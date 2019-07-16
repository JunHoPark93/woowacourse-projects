package techcourse.myblog.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {
    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private int randomLocalPort;

    @BeforeEach
    void setUp() {
        webTestClient.post().uri("/users")
                .body(BodyInserters.fromFormData("name", "Bob")
                        .with("email", "test@gmail.com")
                        .with("password", "PassWord1!"))
                .exchange()
                .expectHeader()
                .valueMatches("location", "http://localhost:" + randomLocalPort + "/login")
                .expectStatus()
                .isFound();
    }

    @Test
    void loginForm() {
        webTestClient.get().uri("/login")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void signUpForm() {
        webTestClient.get().uri("/signup")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 유저_동일한_email() {
        webTestClient.post().uri("/users")
                .body(BodyInserters.fromFormData("name", "Alice")
                        .with("email", "test@gmail.com")
                        .with("password", "PassWord1!"))
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }
}
