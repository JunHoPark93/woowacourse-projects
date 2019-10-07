package slipp.controller;

import nextstep.mvc.tobe.view.ModelAndView;
import nextstep.utils.JsonUtils;
import nextstep.web.annotation.Controller;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;
import slipp.domain.User;
import slipp.support.db.DataBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserRestController {
    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public ModelAndView create(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = JsonUtils.toObject(req.getInputStream(), User.class);
        DataBase.addUser(user);
        resp.setStatus(201);
        resp.addHeader("Location", "/api/users?userId=" + user.getUserId());

        ModelAndView mav = ModelAndView.from();
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public ModelAndView show(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView mav = ModelAndView.from();
        User user = DataBase.findUserById(req.getParameter("userId"));
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.PUT)
    public ModelAndView update(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = DataBase.findUserById(req.getParameter("userId"));
        User updateUser = JsonUtils.toObject(req.getInputStream(), User.class);
        user.update(updateUser);

        ModelAndView mav = ModelAndView.from();
        mav.addObject("user", user);

        return mav;
    }
}
