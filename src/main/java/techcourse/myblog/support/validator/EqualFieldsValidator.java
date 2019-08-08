package techcourse.myblog.support.validator;

import techcourse.myblog.service.dto.request.UserRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

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
        try {
            Object baseFieldValue = getFieldValue(request, baseField);
            Object matchFieldValue = getFieldValue(request, matchField);

            if (!baseFieldValue.equals(matchFieldValue)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("두 필드가 다릅니다")
                        .addPropertyNode(matchField)
                        .addConstraintViolation();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field passwordField = clazz.getDeclaredField(fieldName);
        passwordField.setAccessible(true);
        return passwordField.get(object);
    }
}
