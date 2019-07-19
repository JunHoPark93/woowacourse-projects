package techcourse.myblog.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTests {
    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private int localServerPort;

    @BeforeEach
    void setUp() {
        webTestClient.post().uri("/users")
                .body(BodyInserters.fromFormData("name", "Bob")
                        .with("email", "test@gmail.com")
                        .with("password", "PassWord1!"))
                .exchange()
                .expectStatus()
                .isFound()
        ;
    }

    @Test
    void loginForm() {
        webTestClient.get().uri("/login")
                .exchange()
                .expectStatus().isOk()
        ;
    }

    @Test
    void signUpForm() {
        webTestClient.get().uri("/signup")
                .exchange()
                .expectStatus().isOk()
        ;
    }

    @Test
    void 유저_동일한_email() {
        webTestClient.post().uri("/users")
                .body(BodyInserters.fromFormData("name", "Alice")
                        .with("email", "test@gmail.com")
                        .with("password", "PassWord1!"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(response -> {
                    String body = new String(response.getResponseBody());
                    assertTrue(body.contains("email 중복"));
                })
        ;
    }


    @Test
    void 로그인후_메인화면() {
        webTestClient.post().uri("/login")
                .body(BodyInserters.fromFormData("email", "test@gmail.com")
                        .with("password", "PassWord1!"))
                .exchange()
                .expectStatus().is3xxRedirection();
    }

    @Test
    void 로그인실패_이메일이_없는_경우() {
        webTestClient.post().uri("/login")
                .body(BodyInserters.fromFormData("email", "nothing@gmail.com")
                        .with("password", "PassWord1!"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(response -> {
                    String body = new String(response.getResponseBody());
                    assertTrue(body.contains("email 없음"));
                });
    }

    @Test
    void 로그인실패_비밀번호가_틀린_경우() {
        webTestClient.post().uri("/login")
                .body(BodyInserters.fromFormData("email", "test@gmail.com")
                        .with("password", "PassWord1!!"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(response -> {
                    String body = new String(response.getResponseBody());
                    assertTrue(body.contains("비밀번호 틀림"));
                });
    }

    @Test
    void 로그인안하고_userlist_페이지접근시_로그인화면으로_이동() {
        webTestClient.get().uri("/users")
                .exchange()
                .expectHeader()
                .valueMatches("location", "http://localhost:" + localServerPort + "/login")
                .expectStatus()
                .is3xxRedirection(); // 로그인 화면으로 갈 것임
    }

    @Test
    void 로그인안하고_myPage_페이지접근_로그인화면으로_이동() {
        webTestClient.get().uri("/mypage")
                .exchange()
                .expectHeader()
                .valueMatches("location", "http://localhost:" + localServerPort + "/login")
                .expectStatus()
                .is3xxRedirection(); // 로그인 화면으로 갈 것임
    }

    @Test
    void 로그인안하고_myPage_Edit_페이지접근_로그인화면으로_이동() {
        webTestClient.get().uri("/mypage-edit")
                .exchange()
                .expectHeader()
                .valueMatches("location", "http://localhost:" + localServerPort + "/login")
                .expectStatus()
                .is3xxRedirection(); // 로그인 화면으로 갈 것임
    }


}
