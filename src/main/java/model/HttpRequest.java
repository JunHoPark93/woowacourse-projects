package model;

import utils.HttpMethod;

public class HttpRequest {
    private StartLine startLine;
    private RequestHeader requestHeader;
    private RequestBody requestBody;

    public HttpRequest(StartLine startLine, RequestHeader requestHeader, RequestBody requestBody) {
        this.startLine = startLine;
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
    }

    public HttpRequest(StartLine startLine, RequestHeader requestHeader) {
        this.startLine = startLine;
        this.requestHeader = requestHeader;
    }

    public boolean isSameHttpMethod(HttpMethod httpMethod) {
        return startLine.isSameHttpMethod(httpMethod);
    }

    public String getRequestTarget() {
        return startLine.getTarget();
    }

    public String getHeader(String header) {
        return requestHeader.getHeaderValue(header);
    }
}
