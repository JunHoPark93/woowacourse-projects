package nextstep.mvc.tobe.handler;

import com.google.common.collect.Maps;
import nextstep.mvc.HandlerMapping;
import nextstep.mvc.tobe.util.ComponentScanner;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
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
        Set<Class<?>> annotatedWithController = new ComponentScanner(basePackage).findController();

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

            HandlerExecution handlerExecution = new HandlerExecution(createInstance(clazz), method);
            Arrays.stream(requestMethods)
                    .map(value -> new HandlerKey(requestMapping.value(), value))
                    .forEach(key -> handlerExecutions.put(key, handlerExecution));
        }
    }

    private Object createInstance(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("class has not no-arg constructor");
        }
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMapping) {
        RequestMethod[] requestMethods = requestMapping.method();

        if (requestMethods.length == 0) {
            requestMethods = RequestMethod.values();
        }

        return requestMethods;
    }

    @Override
    public HandlerExecution getHandler(HttpServletRequest request) {
        HandlerKey handlerKey = new HandlerKey(request.getRequestURI(), RequestMethod.valueOf(request.getMethod()));
        return handlerExecutions.get(handlerKey);
    }
}
