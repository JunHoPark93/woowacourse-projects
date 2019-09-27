package webserver.http.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionContextHolder {
    private static final Map<String, HttpSession> context = new ConcurrentHashMap<>();

    public static void bind(String id, HttpSession httpSession) {
        context.put(id, httpSession);
    }

    public static void remove(String id) {
        context.remove(id);
    }

    public static boolean isExists(String id) {
        return context.containsKey(id);
    }

    public static HttpSession get(String id) {
        return context.get(id);
    }
}
