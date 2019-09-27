package webserver.http.request;

import webserver.http.HttpMethod;
import webserver.http.response.Cookie;
import webserver.http.session.HttpSession;
import webserver.http.session.SessionContextHolder;

public final class HttpRequest {
    private RequestLine requestLine;
    private RequestHeader requestHeader;
    private RequestBody requestBody;
    private Cookie cookie;

    private HttpRequest(Builder builder) {
        requestLine = builder.requestLine;
        requestHeader = builder.requestHeader;
        requestBody = builder.requestBody;
        if (requestHeader.isCookieExists()) {
            cookie = CookieParser.parse(requestHeader.getHeaderValue("Cookie"));
            return;
        }
        cookie = Cookie.newInstance();
    }

    public boolean isSameHttpMethod(HttpMethod httpMethod) {
        return requestLine.isSameHttpMethod(httpMethod);
    }

    public HttpSession getSession() {
        String id = cookie.get("session");
        if (checkSessionId(id)) {
            return HttpSession.newInstance();
        }
        return SessionContextHolder.get(id);
    }

    private boolean checkSessionId(String id) {
        return id == null || !SessionContextHolder.isExists(id);
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
        return cookie;
    }

    public static final class Builder {
        private RequestLine requestLine;
        private RequestHeader requestHeader;
        private RequestBody requestBody;

        public Builder() {
        }

        public Builder requestLine(RequestLine val) {
            requestLine = val;
            return this;
        }

        public Builder requestHeader(RequestHeader val) {
            requestHeader = val;
            return this;
        }

        Builder requestBody(RequestBody val) {
            requestBody = val;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }
}
