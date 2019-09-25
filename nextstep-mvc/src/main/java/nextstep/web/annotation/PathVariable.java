package nextstep.web.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PathVariable {
    String value() default "";

    String name() default "";

    boolean required() default true;
}
