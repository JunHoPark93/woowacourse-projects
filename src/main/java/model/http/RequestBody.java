package model.http;

import utils.QueryParser;

import java.util.Collections;
import java.util.Map;

public class RequestBody {
    private Map<String, String> body;

    public RequestBody(String body) {
        this.body = QueryParser.parseRequest(body);
    }

    public Map<String, String> getBody() {
        return Collections.unmodifiableMap(body);
    }
}
