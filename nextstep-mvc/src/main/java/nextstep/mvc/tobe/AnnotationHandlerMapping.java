package nextstep.mvc.tobe;

import com.google.common.collect.Maps;
import nextstep.mvc.HandlerMapping;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {
    private Object[] basePackage;

    private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public void initialize() {
        Set<Class<?>> annotatedWithController = new ControllerScanner(basePackage).findController();

        for (Class<?> clazz : annotatedWithController) {
            Method[] methods = clazz.getDeclaredMethods();
            checkMethod(clazz, methods);
        }
    }

    private void checkMethod(Class<?> clazz, Method[] methods) {
        for (Method method : methods) {
            addHandlerExecution(clazz, method);
        }
    }

    private void addHandlerExecution(Class<?> clazz, Method method) {
        if (method.isAnnotationPresent(RequestMapping.class)) {
            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
            RequestMethod[] requestMethods = getRequestMethods(requestMapping);

            Arrays.stream(requestMethods)
                    .map(value -> new HandlerKey(requestMapping.value(), value))
                    .forEach(key -> handlerExecutions.put(key, new HandlerExecution(clazz, method)));
        }
    }

    private RequestMethod[] getRequestMethods(final RequestMapping requestMapping) {
        RequestMethod[] requestMethods = requestMapping.method();

        if (requestMapping.method().length == 0) {
            requestMethods = RequestMethod.values();
        }

        return requestMethods;
    }

    @Override
    public Handler getHandler(HttpServletRequest request) {
        HandlerKey handlerKey = new HandlerKey(request.getRequestURI(), RequestMethod.valueOf(request.getMethod()));
        return handlerExecutions.get(handlerKey);
    }
}
