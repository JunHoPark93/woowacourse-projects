package slipp.controller;

import nextstep.mvc.tobe.view.ModelAndView;
import nextstep.web.annotation.Controller;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;
import slipp.controller.exception.UserNotFoundException;
import slipp.domain.User;
import slipp.support.db.DataBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @RequestMapping(value = "/users/form", method = RequestMethod.GET)
    public ModelAndView createForm(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mav = ModelAndView.from();
        mav.setViewName("/user/form.jsp");

        return mav;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mav = ModelAndView.from();

        if (!UserSessionUtils.isLogined(req.getSession())) {
            mav.setViewName("redirect:/users/loginForm");
            return mav;
        }

        mav.addObject("users", DataBase.findAll());
        mav.setViewName("/user/list.jsp");

        return mav;
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = DataBase.findUserById(userId);

        ModelAndView mav = ModelAndView.from();

        if (user == null || !user.matchPassword(password)) {
            mav.addObject("loginFailed", true);
            mav.setViewName("/user/login.jsp");
            return mav;
        }

        HttpSession session = req.getSession();
        session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
        mav.setViewName("redirect:/");

        return mav;
    }

    @RequestMapping(value = "/users/profile", method = RequestMethod.GET)
    public ModelAndView showUser(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("사용자를 찾을 수 없습니다.");
        }

        ModelAndView mav = ModelAndView.from();
        mav.addObject("user", user);
        mav.setViewName("/user/profile.jsp");

        return mav;
    }

    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public ModelAndView create(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));
        DataBase.addUser(user);
        ModelAndView mav = ModelAndView.from();
        mav.setViewName("redirect:/");

        return mav;
    }

    @RequestMapping(value = "/users/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.removeAttribute(UserSessionUtils.USER_SESSION_KEY);

        ModelAndView mav = ModelAndView.from();
        mav.setViewName("redirect:/");

        return mav;
    }

    @RequestMapping(value = "/users/updateForm", method = RequestMethod.GET)
    public ModelAndView updateForm(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }

        ModelAndView mav = ModelAndView.from();
        mav.addObject("user", user);
        mav.setViewName("/user/updateForm.jsp");

        return mav;
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public ModelAndView updateUser(HttpServletRequest req, HttpServletResponse resp) {
        User user = DataBase.findUserById(req.getParameter("userId"));
        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }

        User updateUser = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));
        user.update(updateUser);
        ModelAndView mav = ModelAndView.from();
        mav.setViewName("redirect:/");

        return mav;
    }
}