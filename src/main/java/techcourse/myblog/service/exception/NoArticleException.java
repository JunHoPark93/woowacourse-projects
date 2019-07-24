package techcourse.myblog.service.exception;

public class NoArticleException extends RuntimeException {
    public NoArticleException(String message) {
        super(message);
    }
}
