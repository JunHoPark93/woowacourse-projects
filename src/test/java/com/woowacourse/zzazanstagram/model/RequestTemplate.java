package com.woowacourse.zzazanstagram.model;

import com.woowacourse.zzazanstagram.model.support.WebTestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.time.Duration;
import java.util.function.Function;

import static com.woowacourse.zzazanstagram.model.support.WebTestHelper.loginForm;

@ActiveProfiles("test")
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class RequestTemplate {

    @Autowired
    public WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        webTestClient = webTestClient
                .mutate()
                .responseTimeout(Duration.ofMillis(30000))
                .build();
    }

    public WebTestClient.RequestHeadersSpec getHeaderWithLogin(String uri) {
        return webTestClient.get()
                .uri(uri)
                .header("Cookie", getCookie());
    }

    public WebTestClient.RequestHeadersSpec getHeaderWithLogin(Function<UriBuilder, URI> uriFunction) {
        return webTestClient.get()
                .uri(uriFunction)
                .header("Cookie", getCookie());
    }

    public WebTestClient.RequestBodySpec postHeaderWithLogin(String uri) {
        return webTestClient.post()
                .uri(uri)
                .header("Cookie", getCookie());
    }

    public WebTestClient.RequestHeadersSpec deleteHeaderWithLogin(String uri) {
        return webTestClient.delete()
                .uri(uri)
                .header("Cookie", getCookie());
    }

    public WebTestClient.RequestHeadersSpec deleteHeaderWithLogin(String uri, String email, String password) {
        return webTestClient.delete()
                .uri(uri)
                .header("Cookie", getCookie(email, password));
    }

    public WebTestClient.RequestHeadersSpec getRequest(String url) {
        return webTestClient.get().uri(url);
    }

    public WebTestClient.RequestBodySpec postRequest(String url) {
        return webTestClient.post().uri(url);
    }

    private String getCookie() {
        return webTestClient.post().uri("/login")
                .body(loginForm("abc@naver.com", "aa1231!!"))
                .exchange()
                .expectStatus()
                .is3xxRedirection()
                .returnResult(String.class)
                .getResponseHeaders()
                .getFirst("Set-Cookie");
    }

    public void saveOtherMember(String nickName, String email) {
        webTestClient.post().uri("/members")
                .body(WebTestHelper.userSignUpForm(
                        email,
                        "otherUser",
                        "https://image.shutterstock.com/image-photo/bright-spring-view-cameo-island-600w-1048185397.jpg",
                        nickName,
                        "Password!1"))
                .exchange();
    }

    private String getCookie(String email, String password) {
        return webTestClient.post().uri("/login")
                .body(loginForm(email, password))
                .exchange()
                .expectStatus()
                .is3xxRedirection()
                .returnResult(String.class)
                .getResponseHeaders()
                .getFirst("Set-Cookie");
    }
}
