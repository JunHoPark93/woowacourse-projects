package com.woowacourse.zzazanstagram.web.controller.ddabong;

import com.woowacourse.zzazanstagram.model.RequestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

class DdabongControllerTest extends RequestTemplate {

    private static final String CLICKED = "1";
    private static final String UNCLICKED = "0";

    @Test
    void 좋아요_누르기() {
        getDdabongCount("1")
                .jsonPath("$.count").isEqualTo(CLICKED);
    }

    @Test
    void 좋아요_취소() {
        getDdabongCount("2")
                .jsonPath("$.count").isEqualTo(CLICKED);
        getDdabongCount("2")
                .jsonPath("$.count").isEqualTo(UNCLICKED);
    }

    private WebTestClient.BodyContentSpec getDdabongCount(String articleId) {
        return getHeaderWithLogin("/articles/" + articleId + "/ddabongs")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody();
    }
}