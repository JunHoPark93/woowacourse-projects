package techcourse.myblog.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.springframework.web.reactive.function.BodyInserters.fromFormData;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CommentControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    private String cookie;

    @BeforeEach
    void setUp() {
        // 회원가입
        webTestClient.post().uri("/users")
                .body(fromFormData("name", "Bob")
                        .with("email", "test@gmail.com")
                        .with("password", "PassWord1!")
                        .with("reconfirmPassword", "PassWord1!"))
                .exchange()
                .expectStatus()
                .isFound()
        ;

        cookie = getCookie("test@gmail.com");

        // 글쓰기
        String title = "titleTest";
        String coverUrl = "coverUrlTest";
        String contents = "contentsTest";
        String cookie = getCookie("test@gmail.com");
        webTestClient.post()
                .uri("/articles")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters
                        .fromFormData("title", title)
                        .with("coverUrl", coverUrl)
                        .with("contents", contents))
                .header("Cookie", cookie)
                .exchange()
                .expectStatus().isFound();

        // 댓글작성
        webTestClient.post().uri("/comment")
                .body(BodyInserters.fromFormData("contents", "kk")
                        .with("articleId", "1"))
                .header("Cookie", cookie)
                .exchange()
                .expectStatus()
                .isFound();
    }

    @Test
    void 댓글작성자_댓글수정() {
        webTestClient.put().uri("/comment/1")
                .body(BodyInserters.fromFormData("contents", "kk")
                        .with("articleId", "1"))
                .header("Cookie", cookie)
                .exchange()
                .expectStatus()
                .isFound();
    }

    @Test
    void 댓글작성자_댓글삭제() {
        webTestClient.delete().uri("/comment/1")
                .header("Cookie", cookie)
                .exchange()
                .expectStatus()
                .isFound();
    }

    private String getCookie(String email) {
        return webTestClient.post().uri("/login")
                .body(fromFormData("email", email)
                        .with("password", "PassWord1!"))
                .exchange()
                .expectStatus()
                .isFound()
                .returnResult(String.class)
                .getResponseHeaders()
                .getFirst("Set-Cookie");
    }
}
