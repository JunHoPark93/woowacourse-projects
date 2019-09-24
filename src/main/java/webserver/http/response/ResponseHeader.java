package webserver.http.response;

import java.util.HashMap;
import java.util.Map;

public final class ResponseHeader {
    private final Map<String, String> headers;

    public ResponseHeader() {
        this.headers = new HashMap<>();
    }

    public boolean contains(String key) {
        return headers.containsKey(key);
    }

    public void add(String key, String value) {
        headers.put(key, value);
    }

    public String get(String key) {
        return headers.get(key);
    }
}
