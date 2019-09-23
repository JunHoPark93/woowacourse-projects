package webserver.http;

public enum HttpMethod {
    GET(Body.NOT_EXISTS),
    POST(Body.EXISTS),
    PUT(Body.EXISTS),
    DELETE(Body.NOT_EXISTS),
    HEAD(Body.NOT_EXISTS),
    CONNECT(Body.NOT_EXISTS),
    OPTION(Body.NOT_EXISTS),
    TRACE(Body.NOT_EXISTS),
    PATCH(Body.EXISTS);

    private final Body body;

    HttpMethod(Body body) {
        this.body = body;
    }

    public boolean isBodyExists() {
        return body.isExists();
    }
}
