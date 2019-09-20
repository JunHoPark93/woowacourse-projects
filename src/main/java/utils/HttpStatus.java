package utils;

public enum HttpStatus {
    DEFAULT(-1, "NOT INITIALIZED"),
    OK(200, "OK"),
    REDIRECT(302, "FOUND"),
    FORBIDDEN(403, "FORBIDDEN"),
    NOT_FOUND(404, "NOT FOUND"),
    NOT_ALLOWED(405, "NOT ALLOWED");

    private final int value;
    private final String reasonPhrase;

    HttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public boolean isError() {
        return this.equals(NOT_ALLOWED) || this.equals(FORBIDDEN) || this.equals(NOT_FOUND);
    }

    public boolean isNotInitialized() {
        return this.equals(DEFAULT);
    }
}
