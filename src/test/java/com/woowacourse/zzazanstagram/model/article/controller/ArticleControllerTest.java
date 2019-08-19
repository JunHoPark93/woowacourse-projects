package com.woowacourse.zzazanstagram.model.article.controller;

import com.woowacourse.zzazanstagram.model.RequestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static com.woowacourse.zzazanstagram.model.article.ArticleConstant.*;
import static org.assertj.core.api.Assertions.assertThat;

class ArticleControllerTest extends RequestTemplate {

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

        showArticles().expectBody().consumeWith(res -> {
            String body = new String(res.getResponseBody());
            assertThat(body.contains(IMAGE_URL)).isTrue();
        });
    }

    private WebTestClient.ResponseSpec showArticles() {
        return getHeaderWithLogin("/")
                .exchange()
                .expectStatus().isOk();
    }

    private WebTestClient.ResponseSpec createArticle() {
        return postHeaderWithLogin("/articles")
                .body(BodyInserters.fromFormData("image", IMAGE_URL)
                        .with("contents", CONTENTS)
                        .with("hashTag", HASHTAG))
                .exchange();
    }
}