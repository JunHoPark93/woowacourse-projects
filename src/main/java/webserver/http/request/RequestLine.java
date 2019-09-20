package webserver.http.request;

import webserver.http.HttpMethod;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class RequestLine {
    private HttpMethod method;
    private String path;
    private Map<String, String> queryParams = new HashMap<>();
    private String version;

    RequestLine(String method, String path, String version) {
        this.method = HttpMethod.valueOf(method);
        initPath(path);
        this.version = version;
    }

    private void initPath(String path) {
        if (path.contains("?")) {
            this.queryParams = QueryParser.parseRequest(path.substring(path.indexOf("?") + 1));
            path = path.substring(0, path.indexOf("?"));
        }
        this.path = path;
    }

    boolean isBodyExists() {
        return method.isBodyExists();
    }

    boolean isSameHttpMethod(HttpMethod httpMethod) {
        return method.equals(httpMethod);
    }

    String getPath() {
        return path;
    }

    String getDirectory() {
        if (isRootDirectoryRequest()) {
            return "/";
        }
        return path.substring(0, path.lastIndexOf("/"));
    }

    private boolean isRootDirectoryRequest() {
        return path.substring(0, path.lastIndexOf("/")).length() == 0;
    }

    String getResource() {
        if (isRootRequest()) {
            return "index.html";
        }

        return path.substring(path.lastIndexOf("/") + 1);
    }

    private boolean isRootRequest() {
        return path.equals("/");
    }

    HttpMethod getMethod() {
        return method;
    }

    Map<String, String> getQueryParams() {
        return Collections.unmodifiableMap(queryParams);
    }
}
