package nextstep.di.factory;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
        // @Controller @Service @Repo 클래스 반복
        for (Class<?> clazz : this.preInstanticateBeans) {
            Constructor<?> injectedConstructor = BeanFactoryUtils.getInjectedConstructor(clazz);
            if (injectedConstructor == null) {
                continue;
            }
            // 생성자의 파라미터 가져옴
            Parameter[] parameters = injectedConstructor.getParameters();

            List<Object> objects = new ArrayList<>();
            // 파라미터에 해당하는 concrete class 를 찾아옴
            for (Parameter parameter : parameters) {
                Class<?> concreteClass = BeanFactoryUtils.findConcreteClass(parameter.getType(), preInstanticateBeans);

                try {
                    objects.add(concreteClass.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            try {
                beans.put(clazz, injectedConstructor.newInstance(objects.toArray()));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
