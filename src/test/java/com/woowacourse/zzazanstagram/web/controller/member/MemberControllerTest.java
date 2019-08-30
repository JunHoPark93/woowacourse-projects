package com.woowacourse.zzazanstagram.web.controller.member;

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
    void 회원가입_성공() {
        postRequest("/members")
                .body(WebTestHelper.userSignUpForm("newmember@gmail.com",
                        "Jay",
                        "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-600w-1048185397.jpg",
                        "JayJay",
                        "Password!1"))
                .exchange()
                .expectHeader().valueMatches("location", URL_REGEX + "/login" + JSESSIONID_URL)
                .expectStatus().is3xxRedirection();
    }

    @Test
    void 회원가입_실패_잘못된_이메일_형식() {
        postRequest("/members")
                .body(WebTestHelper.userSignUpForm("abc",
                        "myName",
                        "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-600w-1048185397.jpg",
                        "myNick",
                        "aa1231!!"))
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectHeader().valueMatches("location", URL_REGEX + "/signup" + JSESSIONID_URL);
    }

    @Test
    void 회원가입_실패_잘못된_이름_형식() {
        postRequest("/members")
                .body(WebTestHelper.userSignUpForm("abcd@naver.com",
                        "myName234",
                        "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-600w-1048185397.jpg",
                        "myNick",
                        "aa1231!!"))
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectHeader().valueMatches("location", URL_REGEX + "/signup" + JSESSIONID_URL);
    }

    @Test
    void 회원가입_실패_잘못된_닉네임_형식() {
        postRequest("/members")
                .body(WebTestHelper.userSignUpForm("abcd@naver.com",
                        "myName",
                        "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-600w-1048185397.jpg",
                        "myNick!@#",
                        "aa1231!!"))
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectHeader().valueMatches("location", URL_REGEX + "/signup" + JSESSIONID_URL);
    }

    @Test
    void 회원가입_실패_잘못된_패스워드_형식() {
        postRequest("/members")
                .body(WebTestHelper.userSignUpForm("abcd@naver.com",
                        "myName",
                        "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-600w-1048185397.jpg",
                        "myNick",
                        "aaasdgasadg"))
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectHeader().valueMatches("location", URL_REGEX + "/signup" + JSESSIONID_URL);
    }

    @Test
    void 회원가입_실패_잘못된_프로필_형식() {
        postRequest("/members")
                .body(WebTestHelper.userSignUpForm("abcd@naver.com",
                        "myName",
                        "aaa",
                        "myNick",
                        "aa1231!!"))
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectHeader().valueMatches("location", URL_REGEX + "/signup" + JSESSIONID_URL);
    }

    @Test
    void 회원가입_실패_이메일_중복() {
        postRequest("/members")
                .body(WebTestHelper.userSignUpForm("abc@naver.com",
                        "myName",
                        "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-600w-1048185397.jpg",
                        "myNick",
                        "aa1231!!"))
                .exchange()
                .expectHeader().valueMatches("location", URL_REGEX + "/signup" + JSESSIONID_URL)
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
                .expectHeader().valueMatches("location", URL_REGEX + "/signup" + JSESSIONID_URL)
                .expectStatus().is3xxRedirection();
    }
}