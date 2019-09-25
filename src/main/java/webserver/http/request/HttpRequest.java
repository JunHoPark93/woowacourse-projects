package webserver.http.request;

import webserver.http.HttpMethod;
import webserver.http.response.Cookie;

public final class HttpRequest {
    private RequestLine requestLine;
    private RequestHeader requestHeader;
    private RequestBody requestBody;

    private HttpRequest(Builder builder) {
        requestLine = builder.requestLine;
        requestHeader = builder.requestHeader;
        requestBody = builder.requestBody;
    }

    public boolean isSameHttpMethod(HttpMethod httpMethod) {
        return requestLine.isSameHttpMethod(httpMethod);
    }

    public String getPath() {
        return requestLine.getPath();
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

    public String getParameter(String key) {
        if (!isBodyExists()) {
            throw new RuntimeException("Body 가 있는 요청이 아닙니다");
        }
        return requestBody.getParameter(key);
    }

    public String getVersion() {
        return requestLine.getVersion();
    }

    public Cookie getCookie() {
        if (requestHeader.isCookieExists()) {
            return CookieParser.parse(requestHeader.getHeaderValue("Cookie"));
        }
        return Cookie.emptyCookie();
    }

    static final class Builder {
        private RequestLine requestLine;
        private RequestHeader requestHeader;
        private RequestBody requestBody;

        Builder() {
        }

        Builder requestLine(RequestLine val) {
            requestLine = val;
            return this;
        }

        Builder requestHeader(RequestHeader val) {
            requestHeader = val;
            return this;
        }

        Builder requestBody(RequestBody val) {
            requestBody = val;
            return this;
        }

        HttpRequest build() {
            return new HttpRequest(this);
        }
    }
}
