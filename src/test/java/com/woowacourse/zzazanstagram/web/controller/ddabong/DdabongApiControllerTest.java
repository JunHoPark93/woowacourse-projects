package com.woowacourse.zzazanstagram.web.controller.ddabong;

import com.woowacourse.zzazanstagram.model.RequestTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

class DdabongApiControllerTest extends RequestTemplate {

    private static final String CLICKED = "1";
    private static final String UNCLICKED = "0";

    @Test
    void 좋아요_누르기() {
        toggleDdabong("5")
                .jsonPath("$.count").isEqualTo(CLICKED);
    }

    @Test
    void 좋아요_취소() {
        toggleDdabong("6")
                .jsonPath("$.count").isEqualTo(CLICKED);
        toggleDdabong("6")
                .jsonPath("$.count").isEqualTo(UNCLICKED);
    }

    @Test
    void 좋아요한_사람_조회() {
        toggleDdabong("7");
        fetchDdabongMembers("7")
                .jsonPath(".memberResponses").isNotEmpty();
    }

    private WebTestClient.BodyContentSpec toggleDdabong(String articleId) {
        return getHeaderWithLogin("/api/ddabongs/articles/" + articleId)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody();
    }

    private WebTestClient.BodyContentSpec fetchDdabongMembers(String articleId) {
        return getHeaderWithLogin("/api/ddabongs/members/" + articleId)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody();
    }
}