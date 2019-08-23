package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.config.S3MockConfig;
import com.woowacourse.zzazanstagram.model.RequestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.CONTENTS;
import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.HASHTAG;
import static org.assertj.core.api.Assertions.assertThat;

@Import(S3MockConfig.class)
class ArticleControllerTest extends RequestTemplate {
    private ByteArrayResource file;

    @BeforeEach
    public void setUp2() {
        file = new ByteArrayResource(new byte[]{1, 2, 3, 4}) {
            @Override
            public String getFilename() {
                return "test_file.jpg";
            }
        };
    }

    @Test
    void 게시글_등록_페이지_이동_테스트() {
        getHeaderWithLogin("/articles/new")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 게시글_등록이_되는지_테스트() {
        createArticle()
                .expectStatus().is3xxRedirection()
                .expectHeader().valueMatches("Location", "http://[\\w\\d\\.]+:[0-9]+/");
    }

    @Test
    void 게시글_조회_페이지_이동_테스트() {
        showArticles();
    }

    @Test
    void 게시글_조회가_잘되는지_테스트() {
        createArticle();

        showArticles().expectBody()
                .consumeWith(res -> {
                    String body = new String(res.getResponseBody());
                    assertThat(body.contains(CONTENTS)).isTrue();
                });
    }

    private WebTestClient.ResponseSpec showArticles() {
        return getHeaderWithLogin("/")
                .exchange()
                .expectStatus().isOk();
    }

    private WebTestClient.ResponseSpec createArticle() {
        return postHeaderWithLogin("/articles")
                .syncBody(createArticleBody().build())
                .exchange();
    }

    private MultipartBodyBuilder createArticleBody() {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("file", file, MediaType.parseMediaType("image/jpeg"));
        bodyBuilder.part("contents", CONTENTS);
        bodyBuilder.part("hashTag", HASHTAG);

        return bodyBuilder;
    }
}