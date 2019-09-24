package webserver.http.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CookieTest {
    @Test
    void 쿠키_생성() {
        Cookie cookie = new Cookie();
        cookie.add("logined", "true");
        cookie.add("Path", "/");

        String result = "logined=true; Path=/";

        assertThat(cookie.create()).isEqualTo(result);
    }
}