package webserver.http.request;

final class RequestHeader {
    private RequestHeaderParams headers;

    private RequestHeader(RequestHeaderParams headers) {
        this.headers = headers;
    }

    static RequestHeader of(RequestHeaderParams headers) {
        return new RequestHeader(headers);
    }

    String getHeaderValue(String header) {
        return headers.get(header);
    }

    int getContentLength() {
        return Integer.valueOf(headers.get("Content-Length"));
    }
}
