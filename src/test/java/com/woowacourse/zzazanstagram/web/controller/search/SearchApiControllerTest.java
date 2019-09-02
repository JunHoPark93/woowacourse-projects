package com.woowacourse.zzazanstagram.web.controller.search;

import com.woowacourse.zzazanstagram.model.RequestTemplate;
import org.junit.jupiter.api.Test;

class SearchApiControllerTest extends RequestTemplate {

    @Test
    void searchByKeyword() {
        String keyword = "isKing";

        getHeaderWithLogin(
                uriBuilder -> uriBuilder
                        .path("/api/search/" + keyword)
                        .queryParam("maxSizeOfNickName", 1)
                        .queryParam("maxSizeOfHashtag", 1)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.memberResponses[0].nickName").isEqualTo(keyword)
                .jsonPath("$.hashtagResponses[0].keyword").isEqualTo(keyword);
    }
}