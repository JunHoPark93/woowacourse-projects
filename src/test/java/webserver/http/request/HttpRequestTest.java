package webserver.http.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webserver.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpRequestTest {
    private RequestLine requestLine;
    private RequestHeader requestHeader;
    private RequestBody requestBody;

    @BeforeEach
    void setUp() {
        String method = "GET";
        String path = "/index.html";
        String version = "HTTP/1.1";
        requestLine = RequestLine.from(method, path, version);

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Host", "localhost:8080");
        requestHeaders.put("Connection", "keep-alive");
        requestHeaders.put("Accept", "*/*");
        RequestHeaderParams requestHeaderParams = RequestHeaderParams.of(requestHeaders);
        requestHeader = RequestHeader.of(requestHeaderParams);

        String body = "userId=jayandslose&password=password&name=dd&email=test@test.com";
        requestBody = RequestBody.of(body);
    }

    @Test
    void http_request_생성_with_body() {
        HttpRequest httpRequest = HttpRequest.createWithBody(requestLine, requestHeader, requestBody);

        assertThat(httpRequest.getPath()).isEqualTo("/index.html");
        assertThat(httpRequest.getHttpMethod()).isEqualTo(HttpMethod.GET);
        assertThat(httpRequest.getVersion()).isEqualTo("HTTP/1.1");
        assertTrue(httpRequest.isBodyExists());
    }

    @Test
    void http_request_생성_without_body() {
        HttpRequest httpRequest = HttpRequest.createWithoutBody(requestLine, requestHeader);

        assertThat(httpRequest.getPath()).isEqualTo("/index.html");
        assertThat(httpRequest.getHttpMethod()).isEqualTo(HttpMethod.GET);
        assertThat(httpRequest.getVersion()).isEqualTo("HTTP/1.1");
        assertFalse(httpRequest.isBodyExists());
    }
}