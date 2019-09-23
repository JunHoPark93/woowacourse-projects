package webserver.http.request;

import java.util.Map;

public final class RequestHeaderParams {
    private final Map<String, String> headerParams;

    private RequestHeaderParams(Map<String, String> headerParams) {
        this.headerParams = headerParams;
    }

    static RequestHeaderParams of(Map<String, String> headerParams) {
        return new RequestHeaderParams(headerParams);
    }

    public String get(String key) {
        return headerParams.get(key);
    }
}
