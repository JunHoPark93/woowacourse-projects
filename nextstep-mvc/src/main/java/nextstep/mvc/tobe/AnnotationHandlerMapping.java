package nextstep.mvc.tobe;

import com.google.common.collect.Maps;
import nextstep.web.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class AnnotationHandlerMapping {
    private Object[] basePackage;

    private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    public void initialize() {

    }

    public HandlerExecution getHandler(HttpServletRequest request) {
        return null;
    }
}
