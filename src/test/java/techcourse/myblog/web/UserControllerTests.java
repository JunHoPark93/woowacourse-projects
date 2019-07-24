package techcourse.myblog.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.web.reactive.function.BodyInserters.fromFormData;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTests {
    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient.post().uri("/users")
                .body(fromFormData("name", "Bob")
                        .with("email", "test@gmail.com")
                        .with("password", "PassWord1!")
                        .with("reconfirmPassword", "PassWord1!"))
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
                .body(fromFormData("name", "Alice")
                        .with("email", "test@gmail.com")
                        .with("password", "PassWord1!")
                        .with("reconfirmPassword", "PassWord1!"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(response -> {
                    String body = new String(response.getResponseBody());
                    assertTrue(body.contains("이메일 중복입니다"));
                })
        ;
    }

    @Test
    void 로그인후_메인화면() {
        webTestClient.post().uri("/login")
                .body(fromFormData("email", "test@gmail.com")
                        .with("password", "PassWord1!")
                        .with("reconfirmPassword", "PassWord1!"))
                .exchange()
                .expectStatus().is3xxRedirection()
        ;
    }

    @Test
    void 로그인실패_이메일이_없는_경우() {
        webTestClient.post().uri("/login")
                .body(fromFormData("email", "nothing@gmail.com")
                        .with("password", "PassWord1!")
                        .with("reconfirmPassword", "PassWord1!"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(response -> {
                    String body = new String(response.getResponseBody());
                    assertTrue(body.contains("email 없음"));
                })
        ;
    }

    @Test
    void 로그인실패_비밀번호가_틀린_경우() {
        webTestClient.post().uri("/login")
                .body(fromFormData("email", "test@gmail.com")
                        .with("password", "PassWord1!!")
                        .with("reconfirmPassword", "PassWord1!"))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(response -> {
                    String body = new String(response.getResponseBody());
                    assertTrue(body.contains("비밀번호 틀림"));
                })
        ;
    }

    @Test
    void 로그인안하고_userlist_페이지접근시_로그인화면으로_이동() {
        webTestClient.get().uri("/users")
                .exchange()
                .expectHeader()
                .valueMatches("location", ".*/login")
                .expectStatus()
                .is3xxRedirection()
        ; // 로그인 화면으로 갈 것임
    }

    @Test
    void 로그인안하고_myPage_페이지접근_로그인화면으로_이동() {
        webTestClient.get().uri("/mypage")
                .exchange()
                .expectHeader()
                .valueMatches("location", ".*/login")
                .expectStatus()
                .is3xxRedirection()
        ; // 로그인 화면으로 갈 것임
    }

    @Test
    void 로그인안하고_myPage_Edit_페이지접근_로그인화면으로_이동() {
        webTestClient.get().uri("/mypage-edit")
                .exchange()
                .expectHeader()
                .valueMatches("location", ".*/login")
                .expectStatus()
                .is3xxRedirection()
        ; // 로그인 화면으로 갈 것임
    }

    @Test
    void 로그인_요청후_user_list_접근() {
        String cookie = getCookie();

        webTestClient.get().uri("/users")
                .header("Cookie", cookie)
                .exchange()
                .expectStatus().isOk()
        ;
    }

    @Test
    void 로그인_요청후_mypage_접근() {
        String cookie = getCookie();

        webTestClient.get().uri("/mypage")
                .header("Cookie", cookie)
                .exchange()
                .expectStatus().isOk()
        ;
    }

    @Test
    void 로그인_요청후_mypage_edit_접근() {
        String cookie = getCookie();

        webTestClient.get().uri("/mypage-edit")
                .header("Cookie", cookie)
                .exchange()
                .expectStatus().isOk()
        ;
    }

    private String getCookie() {
        return webTestClient.post().uri("/login")
                .body(fromFormData("email", "test@gmail.com")
                        .with("password", "PassWord1!"))
                .exchange()
                .expectStatus()
                .isFound()
                .returnResult(String.class)
                .getResponseHeaders()
                .getFirst("Set-Cookie");
    }
}
