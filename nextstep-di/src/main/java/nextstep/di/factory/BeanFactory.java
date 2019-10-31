package nextstep.di.factory;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);

    private Set<Class<?>> preInstanticateBeans;

    private Map<Class<?>, Object> beans = Maps.newHashMap();

    public BeanFactory(Set<Class<?>> preInstanticateBeans) {
        this.preInstanticateBeans = preInstanticateBeans;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }

    public void initialize() {
        preInstanticateBeans.forEach(this::addBean);
    }

    private void addBean(Class<?> clazz) {
        Constructor<?> injectedConstructor = BeanFactoryUtils.getInjectedConstructor(clazz);
        if (injectedConstructor == null) {
            beans.put(clazz, BeanUtils.instantiateClass(clazz));
            return;
        }
        beans.put(clazz, BeanUtils.instantiateClass(injectedConstructor, createParameterObjects(injectedConstructor).toArray()));
    }

    private List<Object> createParameterObjects(Constructor<?> injectedConstructor) {
        List<Object> parameterObjects = new ArrayList<>();
        Parameter[] parameters = injectedConstructor.getParameters();
        for (Parameter parameter : parameters) {
            initArgs(parameterObjects, parameter);
        }
        return parameterObjects;
    }

    private void initArgs(List<Object> parameterObjects, Parameter parameter) {
        Class<?> concreteClass = BeanFactoryUtils.findConcreteClass(parameter.getType(), preInstanticateBeans);
        if (isBeanInitialized(concreteClass)) {
            parameterObjects.add(beans.get(concreteClass));
            return;
        }
        parameterObjects.add(BeanUtils.instantiateClass(concreteClass));
    }

    private boolean isBeanInitialized(Class<?> concreteClass) {
        return beans.containsKey(concreteClass);
    }
}
