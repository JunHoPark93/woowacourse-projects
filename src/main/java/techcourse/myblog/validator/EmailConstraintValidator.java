package techcourse.myblog.validator;

import techcourse.myblog.domain.UserRepository;
import techcourse.myblog.exception.LoginException;
import techcourse.myblog.exception.SignUpException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailConstraintValidator implements ConstraintValidator<EmailConstraint, String> {
    private UserRepository userRepository;

    public EmailConstraintValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void initialize(EmailConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return !userRepository.existsByEmail(value);
    }
}
