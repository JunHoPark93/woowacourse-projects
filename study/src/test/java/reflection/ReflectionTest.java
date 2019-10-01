package reflection;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionTest {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    public void showClass() throws NoSuchMethodException {
        // TODO Question 클래스의 모든 필드, 생성자, 메소드에 대한 정보를 출력한다.
        Class<Question> clazz = Question.class;
        logger.debug(Arrays.toString(clazz.getDeclaredFields())); // 모든 필드 (접근제한자 상관없이)
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            logger.debug(String.valueOf(constructor));
        }
        logger.debug(Arrays.toString(clazz.getMethods()));

        // 특정 생성자를 가져오는 방법
        Class[] classes = {String.class, String.class, String.class};
        logger.debug(String.valueOf(clazz.getConstructor(classes)));
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void constructor_with_args() throws Exception {
        Class<Question> clazz = Question.class;
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            Class[] parameterTypes = constructor.getParameterTypes();
            logger.debug("paramer length : {}", parameterTypes.length);
            for (Class paramType : parameterTypes) {
                logger.debug("param type : {}", paramType);
            }
        }

        // TODO 인자를 가진 생성자를 활용해 인스턴스를 생성한다.
    }

    @Test
    public void privateFieldAccess() throws Exception {
        Class<Student> clazz = Student.class;

        Student student = new Student();
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(student, "미르제이");

        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        age.set(student, 10);

        assertThat(student.getAge()).isEqualTo(10);
        assertThat(student.getName()).isEqualTo("미르제이");
    }
}
