package nextstep.mvc;

import nextstep.mvc.tobe.JspView;
import nextstep.mvc.tobe.ModelAndView;

public class ViewResolver {
    public static ModelAndView resolve(Object object) {
        if (object instanceof String) {
            return new ModelAndView(new JspView((String) object));
        }

        return (ModelAndView) object;
    }
}