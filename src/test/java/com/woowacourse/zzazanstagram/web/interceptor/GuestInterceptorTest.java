package com.woowacourse.zzazanstagram.web.interceptor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.mock.web.MockHttpServletRequest;

class GuestInterceptorTest extends InterceptorTestTemplate {

    @ParameterizedTest
    @ValueSource(strings = {"/signup", "/login"})
    void 인터셉트_GET_통과(String path) {
        MockHttpServletRequest request = requestWithMock(MockHttpMethod.GET, path);
        checkResponse(200, request);
    }

    @ParameterizedTest
    @ValueSource(strings = {"/members"})
    void 인터셉트_POST_통과(String path) {
        MockHttpServletRequest request = requestWithMock(MockHttpMethod.POST, path);
        checkResponse(200, request);
    }

    @Test
    void 인터셉트_예외() throws Exception {
        MockHttpServletRequest request = requestWithMock(MockHttpMethod.GET, "/");
        checkResponse(302, request);
    }
}