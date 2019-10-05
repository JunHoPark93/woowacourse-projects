package slipp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import support.test.NsWebTestClient;

class UserControllerTest {
    private NsWebTestClient client;

    @BeforeEach
    void setUp() {
        client = NsWebTestClient.of(8080);
    }

    @Test
    void 회원가입() {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("userId", "pobi");
        multiValueMap.add("password", "password");
        multiValueMap.add("name", "pobi");
        multiValueMap.add("email", "pobi@nextstep.camp");

        client.postRequest("/users/create", multiValueMap).isFound();
    }
}