package model.http;

import java.util.HashMap;
import java.util.Map;

public class RequestHeader {
    private Map<String, String> headers = new HashMap<>();

    public void add(String key, String value) {
        headers.put(key, value);
    }

    public String getHeaderValue(String header) {
        return headers.get(header);
    }
}
