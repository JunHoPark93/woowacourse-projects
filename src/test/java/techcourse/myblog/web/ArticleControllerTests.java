package techcourse.myblog.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticleControllerTests {
    @Autowired
    private WebTestClient webTestClient;

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
    void articleSave() {
        webTestClient.post().uri("/articles")
                .body(BodyInserters.fromFormData("title", "z")
                        .with("coverUrl", "z")
                        .with("contents", "z"))
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void articleSelect() {
        webTestClient.get().uri("/articles/0")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void articleModify() {
        webTestClient.get().uri("/articles/0/edit")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void articlePut() {
        webTestClient.put().uri("/articles/2")
                .body(BodyInserters.fromFormData("title", "수정")
                .with("coverUrl", "수정")
                .with("contents", "수정"))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void articleDelete() {
        webTestClient.delete().uri("/articles/3")
                .exchange()
                .expectStatus().is3xxRedirection();
    }

    @Test
    void article_Duplicate_Fail() {
        webTestClient.post().uri("/articles")
                .body(BodyInserters.fromFormData("title", "1번 게시물")
                        .with("coverUrl", "커버")
                        .with("contents", "중복"))
                .exchange()
                .expectStatus().is4xxClientError();
    }
}
