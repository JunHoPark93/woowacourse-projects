package webserver.exception;

public class ResourceLoadException extends RuntimeException {
    public ResourceLoadException(String message) {
        super(message);
    }
}
