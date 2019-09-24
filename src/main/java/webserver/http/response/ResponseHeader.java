package webserver.http.response;

import java.util.Map;

public final class ResponseHeader {
    private final Map<String, String> headers;

    public ResponseHeader(Map<String, String> headers) {
        this.headers = headers;
    }

    public boolean contains(String key) {
        return headers.containsKey(key);
    }

    public String get(String key) {
        return headers.get(key);
    }
}
