package model;

import utils.HttpMethod;

public class RequestLine {

    private HttpMethod method;
    private String target;
    private String version;

    public RequestLine(String method, String target, String version) {
        this.method = HttpMethod.valueOf(method);
        this.target = target;
        this.version = version;
    }

    public boolean isBodyExists() {
        return method.isBodyExists();
    }

    public boolean isSameHttpMethod(HttpMethod httpMethod) {
        return method.equals(httpMethod);
    }

    public String getTarget() {
        return target;
    }
}
