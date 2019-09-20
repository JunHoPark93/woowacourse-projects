package webserver.http.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestBodyTest {

    @Test
    void request_body_초기화() {
        String body = "userId=jayandslose&password=password&name=dd&email=test@test.com";
        RequestBody requestBody = RequestBody.of(body);

        QueryParams queryParams = requestBody.getBody();
        assertThat(queryParams.get("userId")).isEqualTo("jayandslose");
        assertThat(queryParams.get("password")).isEqualTo("password");
        assertThat(queryParams.get("name")).isEqualTo("dd");
        assertThat(queryParams.get("email")).isEqualTo("test@test.com");
    }
}