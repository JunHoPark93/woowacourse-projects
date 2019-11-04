package nextstep.di.factory.exception;

public class InvalidBeanClassTypeException extends RuntimeException {
    public InvalidBeanClassTypeException() {
        super("Error: invalid class type");
    }
}
