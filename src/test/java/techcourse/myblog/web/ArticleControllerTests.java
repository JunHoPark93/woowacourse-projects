package techcourse.myblog.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;
import techcourse.myblog.dto.ArticleDto;

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
        ArticleDto articleDto = new ArticleDto();

        articleDto.setTitle("title");
        articleDto.setCoverUrl("coverUrl");
        articleDto.setContents("contents");

        webTestClient.post().uri("/articles")
                .body(Mono.just(articleDto), ArticleDto.class)
                .exchange()
                .expectStatus().isOk();
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
        ArticleDto articleDto = new ArticleDto();

        articleDto.setTitle("title modified");
        articleDto.setCoverUrl("coverUrl modified");
        articleDto.setContents("contents modified");

        webTestClient.put().uri("/articles/0")
                .body(Mono.just(articleDto), ArticleDto.class)
                .exchange()
                .expectStatus().isOk();
    }
}
