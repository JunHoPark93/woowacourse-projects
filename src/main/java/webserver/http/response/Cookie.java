package webserver.http.response;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Cookie {
    private static final String VALUE_DELIMITER = "=";
    private static final String COOKIE_DELIMITER = "; ";

    private final Map<String, String> cookies;

    public Cookie() {
        // cookie 에 들어오는 값의 순서를 보장한다.
        this.cookies = new LinkedHashMap<>();
    }

    public void add(String key, String value) {
        cookies.put(key, value);
    }

    public String create() {
        return cookies.entrySet().stream()
                .map(e -> e.getKey() + VALUE_DELIMITER + e.getValue())
                .collect(Collectors.joining(COOKIE_DELIMITER));
    }

    public boolean isCookieExists() {
        return cookies.size() != 0;
    }
}
