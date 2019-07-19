package techcourse.myblog.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ArticleControllerTests {
    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private int randomLocalPort;

    @BeforeEach
    void setUp() {
        String title = "titleTest";
        String coverUrl = "coverUrlTest";
        String contents = "contentsTest";
        webTestClient.post()
                .uri("/articles")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters
                        .fromFormData("title", title)
                        .with("coverUrl", coverUrl)
                        .with("contents", contents))
                .exchange()
                .expectStatus().isFound();

    }

    @Test
    void index() {
        webTestClient.get().uri("/")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void articleForm() {
        webTestClient.get().uri("/writing")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 게시글조회() {
        String title = "titleTest";
        String coverUrl = "coverUrlTest";
        String contents = "contentsTest";
        webTestClient.get()
                .uri("/articles/" + "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response2 -> {
                    String body = new String(response2.getResponseBody());
                    assertThat(body.contains(title)).isTrue();
                    assertThat(body.contains(coverUrl)).isTrue();
                    assertThat(body.contains(contents)).isTrue();
                });
    }

    @Test
    void 존재하지_않는_게시글_조회_에러() {
        webTestClient.get()
                .uri("/articles/" + "2")
                .exchange()
                .expectStatus().is5xxServerError();
    }


    @Test
    void 게시글수정페이지() {
        webTestClient.get().uri("/articles/1/edit")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 게시글수정() {
        webTestClient.put().uri("/articles/1")
                .body(BodyInserters.fromFormData("title", "수정")
                        .with("coverUrl", "수정")
                        .with("contents", "수정"))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 게시글삭제() {
        webTestClient.delete().uri("/articles/1")
                .exchange()
                .expectHeader()
                .valueEquals("location", "http://localhost:" + randomLocalPort + "/")
                .expectStatus()
                .is3xxRedirection();
    }

    @Test
    void article_Duplicate_Fail() {
        webTestClient.post().uri("/articles")
                .body(BodyInserters.fromFormData("title", "titleTest")
                        .with("coverUrl", "커버")
                        .with("contents", "중복"))
                .exchange()
                .expectStatus().is4xxClientError();
    }
}
