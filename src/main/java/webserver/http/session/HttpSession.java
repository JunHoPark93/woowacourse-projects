package webserver.http.session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HttpSession {
    private final String id;
    private final Map<String, Object> attributes;

    private HttpSession() {
        this.id = createRandomId();
        this.attributes = new HashMap<>();
    }

    public static HttpSession newInstance() {
        return new HttpSession();
    }

    private String createRandomId() {
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setAttribute(String key, Object value) {
        attributes.put(key, value);
    }

    public Object getAttribute(String key) {
        return attributes.get(key);
    }

    public void removeAttribute(String key) {
        attributes.remove(key);
    }

    public void invalidate() {
        attributes.clear();
    }

    public int size() {
        return attributes.size();
    }
}
