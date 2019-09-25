package webserver.http.request;

import webserver.http.response.Cookie;

public class CookieParser {
    private static final String COOKIE_DELIMITER = "; ";
    private static final String TOKEN_DELIMITER = "=";
    private static final String EMPTY_COOKIE_VALUE = "";
    private static final int EMPTY_VALUE_LENGTH = 1;
    private static final int KEY = 0;
    private static final int VALUE = 1;

    public static Cookie parse(String cookieString) {
        Cookie cookie = Cookie.emptyCookie();
        String[] cookies = cookieString.split(COOKIE_DELIMITER);
        for (String c : cookies) {
            parse(cookie, c);
        }
        return cookie;
    }

    private static void parse(Cookie cookie, String c) {
        String[] split = c.split(TOKEN_DELIMITER);
        if (isCookieValueEmpty(split)) {
            cookie.add(split[KEY], EMPTY_COOKIE_VALUE);
            return;
        }
        cookie.add(split[KEY], split[VALUE]);
    }

    private static boolean isCookieValueEmpty(String[] split) {
        return split.length == EMPTY_VALUE_LENGTH;
    }
}
