package nextstep.mvc.exception;

public class ViewNameNotInitializedException extends RuntimeException {
    public ViewNameNotInitializedException(String message) {
        super(message);
    }
}
