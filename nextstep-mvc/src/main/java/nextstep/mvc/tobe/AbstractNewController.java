package nextstep.mvc.tobe;

import nextstep.mvc.JsonView;
import nextstep.mvc.JspView;
import nextstep.mvc.ModelAndView;

public abstract class AbstractNewController {
    protected ModelAndView jspView(String forwardUrl) {
        return new ModelAndView(new JspView(forwardUrl));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
