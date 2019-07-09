package techcourse.myblog;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void passParamWithGet() {
        String blogName = "helloWrold";
        webTestClient.get().uri("/helloworld?blogName=" + blogName)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(new String(response.getResponseBody())).isEqualTo(blogName));

    }

    @Test
    void passParamWithPost() {
        String blogName = "helloWrold";

        webTestClient.post()
                .uri("/helloworld")
                .body(Mono.just(blogName), String.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(new String(response.getResponseBody())).isEqualTo(blogName));

    }
}
