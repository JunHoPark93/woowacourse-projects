package study.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ParameterNameDiscovererTest {
    @Test
    @DisplayName("메소드 인자 이름을 정상적으로 가져오는지 확인")
    void parametername() {
        ParameterNameDiscoverer nameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

        Class clazz = Sample.class;
        Method[] methods = clazz.getDeclaredMethods();
        Method method = Arrays.stream(methods)
                .filter(m -> m.getName().equals("method"))
                .findFirst().get();
        String[] parameterNames = nameDiscoverer.getParameterNames(method);
        assertThat(parameterNames[0]).isEqualTo("name");
        assertThat(parameterNames[1]).isEqualTo("email");
    }

    private static class Sample {
        public void method(String name, String email) {

        }
    }
}
