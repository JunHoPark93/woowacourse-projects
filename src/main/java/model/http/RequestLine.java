package model.http;

import utils.HttpMethod;
import utils.QueryParser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RequestLine {
    private HttpMethod method;
    private String path;
    private Map<String, String> queryParams = new HashMap<>();
    private String version;

    public RequestLine(String method, String path, String version) {
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
        if (isRootDirectoryRequest()) {
            return "/";
        }
        return path.substring(0, path.lastIndexOf("/"));
    }

    private boolean isRootDirectoryRequest() {
        return path.substring(0, path.lastIndexOf("/")).length() == 0;
    }

    public String getResource() {
        if (isRootRequest()) {
            return "index.html";
        }

        return path.substring(path.lastIndexOf("/") + 1);
    }

    private boolean isRootRequest() {
        return path.equals("/");
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Map<String, String> getQueryParams() {
        return Collections.unmodifiableMap(queryParams);
    }
}
