package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webserver.http.HttpMethod;
import webserver.http.request.HttpRequest;
import webserver.http.request.RequestHeaderParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

class RequestHeaderParserTest {
    private HttpRequest httpRequest;

    @BeforeEach
    void setUp() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/java/data/index_request.txt");
        InputStreamReader inputStream = new InputStreamReader(fileInputStream);

        httpRequest = RequestHeaderParser.parseRequest(inputStream);
    }

    @Test
    void http_method_검사() {
        assertThat(httpRequest.isSameHttpMethod(HttpMethod.GET)).isTrue();
    }

    @Test
    void path_검사() {
        assertThat(httpRequest.getPath()).isEqualTo("/index.html");
    }

    @Test
    void header_검사() {
        assertThat(httpRequest.getHeader("Host")).isEqualTo("localhost:8080");
        assertThat(httpRequest.getHeader("Connection")).isEqualTo("keep-alive");
        assertThat(httpRequest.getHeader("Accept")).isEqualTo("*/*");
    }
}