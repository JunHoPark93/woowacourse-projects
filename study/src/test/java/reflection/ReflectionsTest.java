package reflection;

import annotation.Controller;
import annotation.Repository;
import annotation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class ReflectionsTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionsTest.class);

    @Test
    public void showAnnotationClass() throws Exception {
        Reflections reflections = new Reflections("examples");

        Set<Class<?>> annotatedWithController = reflections.getTypesAnnotatedWith(Controller.class);
        for (Class<?> aClass : annotatedWithController) {
            logger.debug(aClass.getSimpleName());
        }

        Set<Class<?>> annotatedWithService = reflections.getTypesAnnotatedWith(Service.class);
        for (Class<?> aClass : annotatedWithService) {
            logger.debug(aClass.getSimpleName());
        }

        Set<Class<?>> annotatedWithRepository = reflections.getTypesAnnotatedWith(Repository.class);
        for (Class<?> aClass : annotatedWithRepository) {
            logger.debug(aClass.getSimpleName());
        }
    }
}
