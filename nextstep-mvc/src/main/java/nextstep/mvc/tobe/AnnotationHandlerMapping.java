package nextstep.mvc.tobe;

import com.google.common.collect.Maps;
import nextstep.mvc.HandlerMapping;
import nextstep.web.annotation.Controller;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;
import org.reflections.Reflections;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
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
        Set<Class<?>> annotatedWithController = findController();

        for (Class<?> clazz : annotatedWithController) {
            Method[] methods = clazz.getDeclaredMethods();
            checkMethod(clazz, methods);
        }
    }

    private Set<Class<?>> findController() {
        Reflections reflections = new Reflections(basePackage);
        return reflections.getTypesAnnotatedWith(Controller.class);
    }

    private void checkMethod(Class<?> clazz, Method[] methods) {
        for (Method method : methods) {
            checkRequestMapping(clazz, method);
        }
    }

    private void checkRequestMapping(Class<?> clazz, Method method) {
        if (method.isAnnotationPresent(RequestMapping.class)) {
            handlerExecutions.put(createKey(method), new HandlerExecution(clazz, method));
        }
    }

    private HandlerKey createKey(Method method) {
        RequestMapping annotation = method.getAnnotation(RequestMapping.class);
        return new HandlerKey(annotation.value(), annotation.method());
    }

    @Override
    public HandlerExecution getHandler(HttpServletRequest request) {
        HandlerKey handlerKey = new HandlerKey(request.getRequestURI(), RequestMethod.valueOf(request.getMethod()));
        return handlerExecutions.get(handlerKey);
    }
}
