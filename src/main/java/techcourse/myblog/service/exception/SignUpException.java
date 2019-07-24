package techcourse.myblog.service.exception;

public class SignUpException extends RuntimeException {
    public SignUpException(String message) {
        super(message);
    }
}
