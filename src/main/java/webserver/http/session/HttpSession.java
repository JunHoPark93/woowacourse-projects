package webserver.http.session;

import java.util.HashMap;
import java.util.Map;

public class HttpSession {
    private final Map<String, Object> attributes;

    private HttpSession() {
        this.attributes = new HashMap<>();
    }

    public static HttpSession newInstance() {
        return new HttpSession();
    }

    public String getId() {
        return null;
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
