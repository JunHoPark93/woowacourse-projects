package com.woowacourse.zzazanstagram.web.controller.article;

import com.woowacourse.zzazanstagram.model.RequestTemplate;
import org.junit.jupiter.api.Test;

class ArticleApiControllerTest extends RequestTemplate {

    @Test
    void getArticlePages() {
        getHeaderWithLogin(
                uriBuilder -> uriBuilder
                        .path("/api/articles")
                        .queryParam("lastArticleId", 2L)
                        .queryParam("size", 1)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1L);
    }

    @Test
    void getMyPageArticles() {
        getHeaderWithLogin(
                uriBuilder -> uriBuilder
                        .path("/api/articles/mypage")
                        .queryParam("lastArticleId", 2L)
                        .queryParam("size", 1)
                        .queryParam("nickName", "isKing")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1L);
    }
}
