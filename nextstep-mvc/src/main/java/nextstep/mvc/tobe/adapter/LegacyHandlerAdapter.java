package nextstep.mvc.tobe.adapter;

import nextstep.mvc.asis.Controller;
import nextstep.mvc.tobe.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LegacyHandlerAdapter implements HandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return handler instanceof Controller;
    }

    @Override
    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String result = ((Controller) handler).execute(req, resp);
        ModelAndView mav = ModelAndView.from();
        mav.setViewName(result);

        return mav;
    }
}
