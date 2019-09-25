package webserver.http.request;

import webserver.http.HttpMethod;

public final class RequestLine {
    private static final String QUERY_START_DELIMITER = "?";

    private HttpMethod method;
    private String path;
    private QueryParams queryParams;
    private String version;

    private RequestLine(String method, String path, String version) {
        this.method = HttpMethod.valueOf(method);
        initPath(path);
        this.version = version;
    }

    public static RequestLine from(String method, String path, String version) {
        return new RequestLine(method, path, version);
    }

    private void initPath(String path) {
        if (path.contains(QUERY_START_DELIMITER)) {
            this.queryParams = QueryParser.parseRequest(path.substring(path.indexOf(QUERY_START_DELIMITER) + 1));
            path = path.substring(0, path.indexOf(QUERY_START_DELIMITER));
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

    HttpMethod getMethod() {
        return method;
    }

    String getVersion() {
        return version;
    }
}
