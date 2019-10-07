package nextstep.mvc.exception;

public class ViewResolverNotFoundException extends RuntimeException {
    public ViewResolverNotFoundException(String message) {
        super(message);
    }
}
