package nextstep.mvc.tobe;

import nextstep.web.annotation.Controller;
import org.reflections.Reflections;

import java.util.Set;

public class ComponentScanner {
    private Reflections reflections;

    public ComponentScanner(Object[] basePackage) {
        reflections = new Reflections(basePackage);
    }

    public Set<Class<?>> findController() {
        return reflections.getTypesAnnotatedWith(Controller.class);
    }
}
