package model.http;

import utils.HttpMethod;

public class RequestLine {
    private HttpMethod method;
    private String path;
    private String version;

    public RequestLine(String method, String path, String version) {
        this.method = HttpMethod.valueOf(method);
        this.path = path;
        this.version = version;
    }

    public boolean isBodyExists() {
        return method.isBodyExists();
    }

    public boolean isSameHttpMethod(HttpMethod httpMethod) {
        return method.equals(httpMethod);
    }

    public String getPath() {
        return path;
    }

    public String getDirectory() {
        return path.substring(0, path.lastIndexOf("/") + 1);
    }

    public String getResource() {
        // resource가 없는 요청일 경우
        if (isRootRequest()) {
            return "index.html";
        }

        if (isRestRequest()) {
            // TODO rest api
        }

        return path.substring(path.lastIndexOf("/") + 1);
    }

    private boolean isRestRequest() {
        return !path.contains(".");
    }

    private boolean isRootRequest() {
        return path.equals("/");
    }

    public HttpMethod getMethod() {
        return method;
    }
}