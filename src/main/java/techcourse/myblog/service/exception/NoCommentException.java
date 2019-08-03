package techcourse.myblog.service.exception;

public class NoCommentException extends RuntimeException {
    public NoCommentException(String message) {
        super(message);
    }
}
