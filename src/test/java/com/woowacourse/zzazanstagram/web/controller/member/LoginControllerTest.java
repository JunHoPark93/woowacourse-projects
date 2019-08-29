package com.woowacourse.zzazanstagram.web.controller.member;

import com.woowacourse.zzazanstagram.model.RequestTemplate;
import com.woowacourse.zzazanstagram.model.support.WebTestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

class LoginControllerTest extends RequestTemplate {
    private static final String URL_REGEX = "https?://[.\\d\\w]+:?\\d*";
    private static final String JSESSIONID_URL = ";jsessionid=([\\d\\w]+)";

    @Autowired
    public WebTestClient webTestClient;

    @Test
    void 로그인_페이지_이동() {
        getRequest("/login")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 로그인_성공() {
        postRequest("/login")
                .body(WebTestHelper.loginForm("abc@naver.com",
                        "aa1231!!"))
                .exchange()
                .expectHeader().valueMatches("location", URL_REGEX + "/" + JSESSIONID_URL)
                .expectStatus().is3xxRedirection();
    }

    @Test
    void 로그인_실패_존재하지_않는_이메일() {
        postRequest("/login")
                .body(WebTestHelper.loginForm("test10@gmail.com",
                        "Password!1"))
                .exchange()
                .expectHeader().valueMatches("location", URL_REGEX + "/login" + JSESSIONID_URL)
                .expectStatus().is3xxRedirection();
    }

    @Test
    void 로그인_실패_일치하지_않는_비밀번호() {
        postRequest("/login")
                .body(WebTestHelper.loginForm("test@gmail.com",
                        "Password@2"))
                .exchange()
                .expectHeader().valueMatches("location", URL_REGEX + "/login" + JSESSIONID_URL)
                .expectStatus().is3xxRedirection();
    }

    @Test
    void 로그아웃() {
        getHeaderWithLogin("/logout")
                .exchange()
                .expectHeader().valueMatches("location", URL_REGEX + "/login")
                .expectStatus().is3xxRedirection();
    }
}