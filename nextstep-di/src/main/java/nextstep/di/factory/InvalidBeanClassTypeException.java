package nextstep.di.factory;

public class InvalidBeanClassTypeException extends RuntimeException {
    public InvalidBeanClassTypeException() {
        super("Error: invalid class type");
    }
}
