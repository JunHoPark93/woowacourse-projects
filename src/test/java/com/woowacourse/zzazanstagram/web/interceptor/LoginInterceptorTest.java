package com.woowacourse.zzazanstagram.web.interceptor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockHttpServletRequest;

class LoginInterceptorTest extends InterceptorTestTemplate {

    @Test
    void 인터셉트_GET_통과() {
        MockHttpServletRequest request = requestWithMockLoggedIn(MockHttpMethod.GET, "/");
        checkResponse(200, request);
    }

    @Test
    void 인터셉트_POST_통과() {
        MockHttpServletRequest request = requestWithMockLoggedIn(MockHttpMethod.POST, "/members");
        checkResponse(302, request);
    }

    @ParameterizedTest
    @ValueSource(strings = {"/signup", "/login"})
    void 인터셉트_GET_통과못함(String path) {
        MockHttpServletRequest request = requestWithMockLoggedIn(MockHttpMethod.GET, path);
        checkResponse(302, request);
    }
}