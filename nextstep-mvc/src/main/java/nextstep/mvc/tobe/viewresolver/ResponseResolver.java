package nextstep.mvc.tobe.viewresolver;

import nextstep.mvc.tobe.view.ModelAndView;
import nextstep.mvc.tobe.view.View;

public interface ResponseResolver {
    boolean supports(ModelAndView mav);

    View resolve();
}