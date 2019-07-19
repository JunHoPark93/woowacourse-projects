package techcourse.myblog.interceptor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import techcourse.myblog.domain.User;

@AutoConfigureWebTestClient
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthInterceptorTest {
    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToWebHandler(exchange -> {
            String path = exchange.getRequest().getURI().getPath();
            if ("/users".equals(path) || "/mypage".equals(path) || "/mypage-edit".equals(path)) {
                return exchange.getSession()
                        .doOnNext(webSession ->
                                webSession.getAttributes().put("user",
                                        new User("CU", "iloveCU@gmail.com", "PassWord!1")))
                        .then();
            }
            // 이 곳에 분기 추가를 할 수 있음
            return null;
        }).build();
    }

    @Test
    void 로그인후_userlist_페이지_접근() {
        webTestClient.get().uri("/users")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void 로그인후_mypage_페이지_접근() {
        webTestClient.get().uri("/mypage")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void 로그인후_mypage_edit_페이지_접근() {
        webTestClient.get().uri("/mypage-edit")
                .exchange()
                .expectStatus()
                .isOk();
    }
}
