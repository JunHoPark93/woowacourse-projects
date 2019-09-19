package utils;

public enum HttpStatus {
    OK(200, "OK"),
    REDIRECT(302, "FOUND"),
    NOT_ALLOWED(405, "NOT ALLOWED"),
    FORBIDDEN(403, "FORBIDDEN");

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
        return this.equals(NOT_ALLOWED) || this.equals(FORBIDDEN);
    }
}
