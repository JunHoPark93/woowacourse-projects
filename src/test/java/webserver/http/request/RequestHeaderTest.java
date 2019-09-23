package webserver.http.request;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RequestHeaderTest {

    @Test
    void request_header_초기화() {
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Host", "localhost:8080");
        requestHeaders.put("Connection", "keep-alive");
        requestHeaders.put("Accept", "*/*");

        RequestHeaderParams requestHeaderParams = RequestHeaderParams.of(requestHeaders);
        RequestHeader requestHeader = RequestHeader.of(requestHeaderParams);

        assertThat(requestHeader.getHeaderValue("Host")).isEqualTo("localhost:8080");
        assertThat(requestHeader.getHeaderValue("Connection")).isEqualTo("keep-alive");
        assertThat(requestHeader.getHeaderValue("Accept")).isEqualTo("*/*");
    }
}