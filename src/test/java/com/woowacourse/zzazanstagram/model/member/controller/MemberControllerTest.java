package com.woowacourse.zzazanstagram.model.member.controller;

import com.woowacourse.zzazanstagram.model.RequestTemplate;
import com.woowacourse.zzazanstagram.model.support.WebTestHelper;
import org.junit.jupiter.api.Test;

class MemberControllerTest extends RequestTemplate {
    private static final String URL_REGEX = "https?://[.\\d\\w]+:?\\d*";
    private static final String JSESSIONID_URL = ";jsessionid=([\\d\\w]+)";

    @Test
    void 회원가입_페이지_이동() {
        getRequest("/signup")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 로그인_페이지_이동() {
        getRequest("/login")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void 회원가입_성공() {
        postRequest("/members")
                .body(WebTestHelper.userSignUpForm("newmember@gmail.com",
                        "Jay",
                        "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-600w-1048185397.jpg",
                        "JayJay",
                        "Password!1"))
                .exchange()
                .expectHeader().valueMatches("location", URL_REGEX + "/login")
                .expectStatus().is3xxRedirection();
    }

    @Test
    void 회원가입_실패_이메일_중복() {
        postRequest("/members")
                .body(WebTestHelper.userSignUpForm("test@gmail.com",
                        "myName",
                        "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-600w-1048185397.jpg",
                        "myNick",
                        "Password!1"))
                .exchange()
                .expectHeader().valueMatches("location", URL_REGEX + "/signup")
                .expectStatus().is3xxRedirection();
    }

    @Test
    void 회원가입_실패_닉네임_중복() {
        postRequest("/members")
                .body(WebTestHelper.userSignUpForm("test3@gmail.com",
                        "myName",
                        "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-600w-1048185397.jpg",
                        "myNick",
                        "Password!1"))
                .exchange()
                .expectHeader().valueMatches("location", URL_REGEX + "/signup")
                .expectStatus().is3xxRedirection();
    }

    @Test
    void 로그인_성공() {
        postRequest("/login")
                .body(WebTestHelper.loginForm("test@gmail.com",
                        "Password!1"))
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