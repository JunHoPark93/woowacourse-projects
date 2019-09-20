package webserver.http.request;

import webserver.http.HttpMethod;

final class RequestLine {
    private HttpMethod method;
    private String path;
    private QueryParams queryParams;
    private String version;

    private RequestLine(String method, String path, String version) {
        this.method = HttpMethod.valueOf(method);
        initPath(path);
        this.version = version;
    }

    static RequestLine from(String method, String path, String version) {
        return new RequestLine(method, path, version);
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

    QueryParams getQueryParams() {
        return queryParams;
    }

    String getVersion() {
        return version;
    }
}
