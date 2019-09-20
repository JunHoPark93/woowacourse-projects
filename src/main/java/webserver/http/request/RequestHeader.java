package webserver.http.request;

import java.util.HashMap;
import java.util.Map;

class RequestHeader {
    private Map<String, String> headers = new HashMap<>();

    void add(String key, String value) {
        headers.put(key, value);
    }

    String getHeaderValue(String header) {
        return headers.get(header);
    }

    int getContentLength() {
        return Integer.valueOf(headers.get("Content-Length"));
    }
}
