package nextstep.mvc.tobe;

import nextstep.web.annotation.Controller;
import org.reflections.Reflections;

import java.util.Set;

public class ControllerScanner {
    private Reflections reflections;

    public ControllerScanner(Object[] basePackage) {
        reflections = new Reflections(basePackage);
    }

    public Set<Class<?>> findController() {
        return reflections.getTypesAnnotatedWith(Controller.class);
    }
}
