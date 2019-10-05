package slipp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import support.test.NsWebTestClient;

class HomeControllerTest {
    private NsWebTestClient client;

    @BeforeEach
    void setUp() {
        client = NsWebTestClient.of(8080);
    }

    @Test
    void 메인페이지() {
        client.getRequest("/").isOk();
    }

    @Test
    void 로그인페이지() {
        client.getRequest("/users/loginForm").isOk();
    }
}