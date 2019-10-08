package nextstep.mvc.tobe.viewresolver;

import nextstep.mvc.tobe.view.JsonView;
import nextstep.mvc.tobe.view.ModelAndView;
import nextstep.mvc.tobe.view.View;

public class JsonResponseResolver implements ResponseResolver {
    @Override
    public boolean supports(ModelAndView mav) {
        return !mav.isViewNameExist(); // view 가 없을 때 JsonViewResolver
    }

    @Override
    public View resolve() {
        return new JsonView();
    }
}
