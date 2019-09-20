package webserver.http.request;

import webserver.http.HttpMethod;

public final class HttpRequest {
    private RequestLine requestLine;
    private RequestHeader requestHeader;
    private RequestBody requestBody;

    private HttpRequest(RequestLine requestLine, RequestHeader requestHeader, RequestBody requestBody) {
        this.requestLine = requestLine;
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
    }

    private HttpRequest(RequestLine requestLine, RequestHeader requestHeader) {
        this.requestLine = requestLine;
        this.requestHeader = requestHeader;
    }

    static HttpRequest createWithBody(RequestLine requestLine, RequestHeader requestHeader, RequestBody requestBody) {
        return new HttpRequest(requestLine, requestHeader, requestBody);
    }

    static HttpRequest createWithoutBody(RequestLine requestLine, RequestHeader requestHeader) {
        return new HttpRequest(requestLine, requestHeader);
    }

    public boolean isSameHttpMethod(HttpMethod httpMethod) {
        return requestLine.isSameHttpMethod(httpMethod);
    }

    public String getResource() {
        return requestLine.getResource();
    }

    public String getPath() {
        return requestLine.getPath();
    }

    public String getDirectory() {
        return requestLine.getDirectory();
    }

    public QueryParams getQueryParams() {
        return requestLine.getQueryParams();
    }

    public HttpMethod getHttpMethod() {
        return requestLine.getMethod();
    }

    public String getHeader(String header) {
        return requestHeader.getHeaderValue(header);
    }

    public boolean isBodyExists() {
        return requestBody != null;
    }

    public QueryParams getBody() {
        if (requestBody != null) {
            return requestBody.getBody();
        }
        throw new RuntimeException("Body 가 있는 요청이 아닙니다");
    }

    public String getVersion() {
        return requestLine.getVersion();
    }
}
