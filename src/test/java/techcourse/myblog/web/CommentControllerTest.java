package techcourse.myblog.web;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import techcourse.myblog.service.dto.request.CommentRequest;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static techcourse.myblog.web.WebTestHelper.*;

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
                .body(signUpForm("CU", "love@gmail.com", "PassWord!1"))
                .exchange()
                .expectStatus()
                .isFound()
        ;

        cookie = getCookie("love@gmail.com");

        // 글쓰기
        webTestClient.post()
                .uri("/articles")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(articleForm("title", "http://woowabros.github.io/img/2019-02-08/techcourse_poster.jpeg", "contents"))
                .header("Cookie", cookie)
                .exchange()
                .expectStatus().isFound();
    }

    @Test
    void 댓글달기() throws IOException {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setContents("dfdf");
        commentRequest.setArticleId(1L);

        EntityExchangeResult<byte[]> entityExchangeResult = saveComment(commentRequest);
        String body = new String(Objects.requireNonNull(entityExchangeResult.getResponseBody()));

        ObjectMapper objectMapper = new ObjectMapper();
        List responses = objectMapper.readValue(body, List.class);

        assertThat(responses).hasSize(1);
    }

    @Test
    void 댓글수정() {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setContents("dfdf");
        commentRequest.setArticleId(1L);
        saveComment(commentRequest);

        webTestClient.put().uri("/comment/1")
                .body(commentForm())
                .header("Cookie", cookie)
                .exchange()
                .expectStatus()
                .isFound();
    }

    @Test
    void 댓글삭제() throws IOException {
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setContents("1st");
        commentRequest.setArticleId(1L);
        saveComment(commentRequest);

        CommentRequest commentRequest2 = new CommentRequest();
        commentRequest2.setContents("2nd");
        commentRequest2.setArticleId(1L);
        saveComment(commentRequest2);

        CommentRequest commentRequest3 = new CommentRequest();
        commentRequest3.setContents("3rd");
        commentRequest3.setArticleId(1L);
        saveComment(commentRequest3);

        EntityExchangeResult<byte[]> entityExchangeResult = webTestClient.delete().uri("/comment/2")
                .header("Cookie", this.cookie)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .returnResult();

        String body = new String(Objects.requireNonNull(entityExchangeResult.getResponseBody()));
        ObjectMapper objectMapper = new ObjectMapper();
        List responses = objectMapper.readValue(body, List.class);

        assertThat(responses).hasSize(2);
    }

    private EntityExchangeResult<byte[]> saveComment(CommentRequest commentRequest) {
        return webTestClient.post().uri("/comment")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(commentRequest), CommentRequest.class)
                .header("Cookie", cookie)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .returnResult();
    }

    private String getCookie(String email) {
        return webTestClient.post().uri("/login")
                .body(loginForm(email, "PassWord!1"))
                .exchange()
                .expectStatus()
                .isFound()
                .returnResult(String.class)
                .getResponseHeaders()
                .getFirst("Set-Cookie");
    }
}
