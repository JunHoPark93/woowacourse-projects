package webserver.http.request;

import org.junit.jupiter.api.Test;
import webserver.http.HttpMethod;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RequestLineTest {

    @Test
    void request_line_초기화() {
        String method = "GET";
        String path = "/index.html";
        String version = "HTTP/1.1";

        RequestLine requestLine = RequestLine.from(method, path, version);

        assertFalse(requestLine.isBodyExists());
        assertTrue(requestLine.isSameHttpMethod(HttpMethod.GET));
        assertThat(requestLine.getResource()).isEqualTo("index.html");
    }
}