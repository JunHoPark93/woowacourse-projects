package webserver.http.response;

import org.junit.jupiter.api.Test;
import webserver.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpResponseTest {

    @Test
    void 에러_response_생성() {
        HttpResponse httpResponse = HttpResponse.createError();

        assertTrue(httpResponse.hasError());
    }

    @Test
    void response_생성() {
        HttpResponse httpResponse = HttpResponse.of();
        httpResponse.sendRedirect("/index.html", HttpStatus.OK);

        assertFalse(httpResponse.hasError());
        assertThat(httpResponse.getLocation()).isEqualTo("/index.html");
    }
}