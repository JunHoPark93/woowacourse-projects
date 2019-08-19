package com.woowacourse.zzazanstagram.model;

import com.woowacourse.zzazanstagram.model.support.WebTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.woowacourse.zzazanstagram.model.support.WebTestHelper.loginForm;

@ActiveProfiles("test")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class RequestTemplate {
    private static String email;

    @Autowired
    public WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        if (email == null) {
            webTestClient.post().uri("/members")
                    .body(WebTestHelper.userSignUpForm("test@gmail.com",
                            "myName",
                            "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-600w-1048185397.jpg",
                            "myNick",
                            "Password!1"))
                    .exchange();
            email = "test@gmail.com";
        }
    }

    public WebTestClient.RequestHeadersSpec<?> getHeaderWithLogin(String uri) {
        return webTestClient.get()
                .uri(uri)
                .header("Cookie", getCookie());
    }

    public WebTestClient.RequestBodySpec postHeaderWithLogin(String uri) {
        return webTestClient.post()
                .uri(uri)
                .header("Cookie", getCookie());
    }

    public WebTestClient.RequestHeadersSpec<?> getRequest(String url) {
        return webTestClient.get().uri(url);
    }

    public WebTestClient.RequestBodySpec postRequest(String url) {
        return webTestClient.post().uri(url);
    }

    private String getCookie() {
        return webTestClient.post().uri("/login")
                .body(loginForm("test@gmail.com", "Password!1"))
                .exchange()
                .expectStatus()
                .isFound()
                .returnResult(String.class)
                .getResponseHeaders()
                .getFirst("Set-Cookie");
    }
}
