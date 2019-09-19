package utils;

public enum HttpStatus {

    OK(200, "ok"),
    NOT_ALLOWED(405, "not allowed"),
    FORBIDDEN(403, "forbidden");

    private final int value;
    private final String reasonPhrase;

    HttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }
}
