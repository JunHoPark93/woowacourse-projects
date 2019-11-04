package nextstep.di.factory;

import com.google.common.collect.Maps;
import nextstep.di.factory.exception.DefaultConstructorInitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);

    private Set<Class<?>> preInstanticateBeans;

    private Map<Class<?>, Object> beans = Maps.newHashMap();

    public BeanFactory(Set<Class<?>> preInstanticateBeans) {
        this.preInstanticateBeans = preInstanticateBeans;
    }

    public BeanFactory(Scanner scanner) {
        this.preInstanticateBeans = scanner.getAnnotatedClasses();
    }

    public Map<Class<?>, Object> getBeansWithType(Class<? extends Annotation> type) {
        return this.beans.entrySet().stream()
                .filter(x -> x.getKey().isAnnotationPresent(type))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }

    public void initialize() {
        for (Class<?> bean : preInstanticateBeans) {
            beans.put(bean, createInstance(bean));
        }
    }

    private Object createInstance(Class<?> clazz) {
        if (isBeanExists(clazz)) {
            return getBean(clazz);
        }
        Constructor<?> constructor = getConstructor(clazz);
        List<Object> paramInstances = initParameters(constructor);
        return BeanUtils.instantiateClass(constructor, paramInstances.toArray());
    }

    private boolean isBeanExists(Class<?> clazz) {
        return getBean(clazz) != null;
    }

    private Constructor<?> getConstructor(Class<?> clazz) {
        Constructor injectedConstructor = BeanFactoryUtils.getInjectedConstructor(clazz);
        if (injectedConstructor == null) {
            return getDefaultConstructor(clazz);
        }
        return injectedConstructor;
    }

    private Constructor getDefaultConstructor(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            logger.error("Error : {0}", e);
            throw new DefaultConstructorInitException(e);
        }
    }

    private List<Object> initParameters(Constructor constructor) {
        Parameter[] parameters = constructor.getParameters();
        List<Object> paramInstances = new ArrayList<>();
        for (Parameter parameter : parameters) {
            Class<?> clazz = BeanFactoryUtils.findConcreteClass(parameter.getType(), preInstanticateBeans);
            paramInstances.add(createInstance(clazz));
        }
        return paramInstances;
    }
}
