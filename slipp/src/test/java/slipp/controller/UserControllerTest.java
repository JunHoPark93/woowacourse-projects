package slipp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import support.test.NsWebTestClient;

class UserControllerTest {
    private NsWebTestClient client;
    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    @DisplayName("회원가입")
    void setUp() {
        client = NsWebTestClient.of(8080);
        multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("userId", "pobi");
        multiValueMap.add("password", "password");
        multiValueMap.add("name", "pobi");
        multiValueMap.add("email", "pobi@nextstep.camp");

        client.postRequest("/users/create", multiValueMap)
                .isFound()
                .expectHeader()
                .valueEquals("Location", "/");
    }

    @Test
    void 회원가입_페이지_이동() {
        client.getRequest("/users/form").isOk();
    }

    @Test
    void 로그인_성공() {
        client.postRequest("/users/login", multiValueMap)
                .isFound()
                .expectHeader()
                .valueEquals("Location", "/");
    }

    @Test
    void 로그인_실패() {
        MultiValueMap<String, String> wrongLogin = new LinkedMultiValueMap<>();
        wrongLogin.add("userId", "pobi");
        wrongLogin.add("password", "passwordwrong");
        wrongLogin.add("name", "pobi");
        wrongLogin.add("email", "pobi@nextstep.camp");

        client.postRequest("/users/login", wrongLogin).isOk();

    }

    @Test
    void 로그인_회원이_없는_경우() {
        multiValueMap.clear();
        client.postRequest("/users/login", multiValueMap).isOk();
    }

    @Test
    void 로그인_하지_않고_회원_리스트_페이지_이동() {
        client.getRequest("/users")
                .isFound()
                .expectHeader()
                .valueEquals("Location", "/users/loginForm");
    }

    @Test
    void 로그인_하고_회원_리스트_페이지_이동() {
        client.loginGetRequest("/users", multiValueMap).isOk();
    }

    @Test
    void 사용자_조회() {
        client.getRequest("/users/profile?userId=pobi").isOk();
    }

    @Test
    void 사용자_프로필_조회_실패() {
        client.getRequest("/users/profile?userId=pobiii").is5xxServerError();
    }

    @Test
    void 로그아웃() {
        client.getRequest("/users/logout").isFound();
    }

    @Test
    void 업데이트_페이지_이동() {
        client.loginGetRequest("/users/updateForm?userId=pobi", multiValueMap).isOk();
    }

    @Test
    void 업데이트_페이지_다른사람_접근() {
        client.loginGetRequest("/users/updateForm?userId=pobiii", multiValueMap).is5xxServerError();
    }

    @Test
    void 사용자_정보_수정() {
        MultiValueMap<String, String> updateUserData = new LinkedMultiValueMap<>();
        updateUserData.add("userId", "pobi");
        updateUserData.add("password", "jaypass");
        updateUserData.add("name", "jay");
        updateUserData.add("email", "jay@gmail.com");

        client.loginPostRequest("/users/update", multiValueMap, updateUserData).isFound();
    }

    @Test
    void 다른_사용자_정보_수정_시도() {
        MultiValueMap<String, String> updateUserData = new LinkedMultiValueMap<>();
        updateUserData.add("userId", "another");
        updateUserData.add("password", "jaypass");
        updateUserData.add("name", "jay");
        updateUserData.add("email", "jay@gmail.com");

        client.loginPostRequest("/users/update", multiValueMap, updateUserData).is5xxServerError();
    }
}