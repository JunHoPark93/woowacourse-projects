package techcourse.myblog.support.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TitleConstraintValidator.class)
public @interface TitleConstraint {
    String message() default "유효하지 않은 제목 입니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
