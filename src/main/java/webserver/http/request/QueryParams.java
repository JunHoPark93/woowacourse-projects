package webserver.http.request;

import java.util.Map;

public final class QueryParams {
    private final Map<String, String> queryParams;

    QueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    public String get(String key) {
        return queryParams.get(key);
    }
}
