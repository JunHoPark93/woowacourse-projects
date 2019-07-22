package techcourse.myblog.support.validator;

import techcourse.myblog.service.dto.UserRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EqualFieldsValidator implements ConstraintValidator<EqualFields, UserRequest> {
    private String baseField;
    private String matchField;

    @Override
    public void initialize(EqualFields constraintAnnotation) {
        baseField = constraintAnnotation.baseField();
        matchField = constraintAnnotation.matchField();
    }

    @Override
    public boolean isValid(UserRequest request, ConstraintValidatorContext context) {
        if (!baseField.equals(matchField)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("두 필드가 다릅니다")
                    .addPropertyNode(matchField)
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
