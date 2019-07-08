package com.techcourse.myblog;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void passParamWithGet() throws Exception {
        String blogName = "helloWrold";
        webTestClient.get().uri("/helloworld?blogName=" + blogName)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(new String(response.getResponseBody())).isEqualTo(blogName));

    }

    @Test
    public void passParamWithPost() throws Exception {
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
