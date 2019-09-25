package webserver.http.request;

public final class RequestHeader {
    private static final String COOKIE = "Cookie";

    private final RequestHeaderParams headers;

    private RequestHeader(RequestHeaderParams headers) {
        this.headers = headers;
    }

    public static RequestHeader of(RequestHeaderParams headers) {
        return new RequestHeader(headers);
    }

    String getHeaderValue(String header) {
        return headers.get(header);
    }

    int getContentLength() {
        return Integer.valueOf(headers.get("Content-Length"));
    }

    boolean isCookieExists() {
        return headers.get(COOKIE) != null;
    }
}
