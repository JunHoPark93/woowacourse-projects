package webserver.http.response;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Cookie {
    private static final String VALUE_DELIMITER = "=";
    private static final String EMPTY_VALUE = "";
    private static final String HTTP_ONLY = "HttpOnly";

    private final Map<String, String> cookies;

    private Cookie() {
        // cookie 에 들어오는 값의 순서를 보장한다.
        this.cookies = new LinkedHashMap<>();
    }

    public static Cookie newInstance() {
        return new Cookie();
    }

    public void add(String key, String value) {
        cookies.put(key, value);
    }

    public void addAttribute(String key, String addValue) {
        cookies.put(key, cookies.get(key) + "; " + addValue);
    }

    /**
     * cookie 의 이름을 찾아 해당 쿠키를 httponly 로 설정한다.
     *
     * @param key cookie 이름
     */
    public void setHttpOnly(String key) {
        cookies.put(key, cookies.get(key) + "; " + HTTP_ONLY);
    }

    public String create() {
        return cookies.entrySet().stream()
                .map(e -> e.getKey() + VALUE_DELIMITER + e.getValue())
                .collect(Collectors.joining("\r\n"));
    }

    boolean isCookieExists() {
        return cookies.size() != 0;
    }

    public String get(String key) {
        String value = cookies.get(key);
        return value != null ? value : EMPTY_VALUE;
    }
}
