package slipp.controller;

import nextstep.mvc.tobe.view.ModelAndView;
import nextstep.web.annotation.Controller;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;
import slipp.domain.User;
import slipp.support.db.DataBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public ModelAndView create(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));

        DataBase.addUser(user);

        ModelAndView mav = ModelAndView.from();
        mav.setViewName("redirect:/");
        return mav;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public ModelAndView create2(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));
        DataBase.addUser(user);

        resp.setStatus(201);
        ModelAndView mav = ModelAndView.from();
        mav.addObject("user", user);

        return mav;
    }

    // TODO GET
    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public void create3(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));

        DataBase.addUser(user);
        resp.setStatus(404);
        //new JsonView().render(new HashMap<>(), req, resp);
    }
}