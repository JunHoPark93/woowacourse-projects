package webserver.http.request;

import java.util.Collections;
import java.util.Map;

public class RequestBody {
    private Map<String, String> body;

    RequestBody(String body) {
        this.body = QueryParser.parseRequest(body);
    }

    public Map<String, String> getBody() {
        return Collections.unmodifiableMap(body);
    }
}
