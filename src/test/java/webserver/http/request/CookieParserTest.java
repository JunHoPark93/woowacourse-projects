package webserver.http.request;

import org.junit.jupiter.api.Test;
import webserver.http.response.Cookie;

import static org.assertj.core.api.Assertions.assertThat;

class CookieParserTest {
    @Test
    void cookie_파싱() {
        String cookieFromClient =
                "Idea-46e357f3=12ba8a78-2c7c-4424-8bf8-cdcddde5d495; " +
                "m=; " + "Idea-46e35bb3=0c1eac4c-4893-4f97-b669-c14ddae9ecf9; " + "logined=true";

        Cookie cookie = CookieParser.parse(cookieFromClient);

        assertThat(cookie.get("logined")).isEqualTo("true");
        assertThat(cookie.get("m")).isEqualTo("");
    }
}