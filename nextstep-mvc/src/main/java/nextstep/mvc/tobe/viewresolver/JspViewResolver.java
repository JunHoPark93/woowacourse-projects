package nextstep.mvc.tobe.viewresolver;

import nextstep.mvc.exception.ViewNameNotInitializedException;
import nextstep.mvc.tobe.view.JspView;
import nextstep.mvc.tobe.view.ModelAndView;
import nextstep.mvc.tobe.view.View;

public class JspViewResolver implements ViewResolver {
    private static final String JSP_SUFFIX = ".jsp";
    private static final String REDIRECT_PREFIX = "redirect:";

    private String viewName;

    @Override
    public boolean supports(ModelAndView mav) {
        if (isJspSuffix(mav) || isRedirectRequest(mav)) {
            this.viewName = mav.getViewName();
            return true;
        }
        return false;
    }

    private boolean isJspSuffix(ModelAndView mav) {
        return mav.getViewName().endsWith(JSP_SUFFIX);
    }

    private boolean isRedirectRequest(ModelAndView mav) {
        return mav.getViewName().startsWith(REDIRECT_PREFIX);
    }

    @Override
    public View resolve() {
        if (viewName == null) {
            throw new ViewNameNotInitializedException("view name not initialized");
        }
        return new JspView(viewName);
    }
}
