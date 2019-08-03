package techcourse.myblog.service.exception;

public class InvalidAuthorException extends RuntimeException {
    public InvalidAuthorException(String message) {
        super(message);
    }
}
