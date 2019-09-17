package utils;

public enum HttpMethod {
    GET(false),
    POST(true),
    PUT(true),
    DELETE(false),
    HEAD(false),
    CONNECT(false),
    OPTION(false),
    TRACE(false),
    PATCH(true);

    boolean isBodyExists;

    HttpMethod(boolean isBodyExists) {
        this.isBodyExists = isBodyExists;
    }

    public boolean isBodyExists() {
        return isBodyExists;
    }
}
