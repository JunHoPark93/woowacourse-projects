package com.woowacourse.zzazanstagram.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class InterceptorTestTemplate {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    private MockHttpServletRequest request;

    public MockHttpServletRequest requestWithMock(MockHttpMethod mockHttpMethod, String uri) {
        request = new MockHttpServletRequest();
        request.setMethod(mockHttpMethod.getMethod());
        request.setRequestURI(uri);
        return request;
    }

    public MockHttpServletRequest requestWithMockLoggedIn(MockHttpMethod mockHttpMethod, String uri) {
        request = new MockHttpServletRequest();
        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("member", "test");
        MockHttpServletRequest request = requestWithMock(mockHttpMethod, uri);
        request.setSession(mockHttpSession);
        return request;
    }

    public void checkResponse(int expectedCode, MockHttpServletRequest request) {
        MockHttpServletResponse response = new MockHttpServletResponse();

        HandlerExecutionChain handlerExecutionChain;
        try {
            handlerExecutionChain = handlerMapping.getHandler(request);
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        HandlerInterceptor[] interceptors = handlerExecutionChain.getInterceptors();
        for (HandlerInterceptor interceptor : interceptors) {
            try {
                interceptor.preHandle(request, response, handlerExecutionChain.getHandler());
            } catch (Exception e) {
                throw new IllegalArgumentException("잘못된 접근입니다.");
            }
        }

        assertEquals(expectedCode, response.getStatus());
    }

    public enum MockHttpMethod {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        DELETE("DELETE");

        private String method;

        MockHttpMethod(String method) {
            this.method = method;
        }

        public String getMethod() {
            return method;
        }
    }
}
