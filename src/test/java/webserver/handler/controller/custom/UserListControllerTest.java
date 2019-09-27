package webserver.handler.controller.custom;

import db.DataBase;
import model.User;
import org.junit.jupiter.api.Test;
import webserver.handler.controller.Controller;
import webserver.handler.controller.HttpRequestHelper;
import webserver.http.HttpStatus;
import webserver.http.request.HttpRequest;
import webserver.http.request.RequestHeader;
import webserver.http.request.RequestHeaderParams;
import webserver.http.request.RequestLine;
import webserver.http.response.HttpResponse;
import webserver.view.HandlebarResourceResolver;
import webserver.view.TemplateResourceResolver;
import webserver.view.ViewResolver;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class UserListControllerTest {
    private static final String COOKIE_DELIMITER = "; ";
    private static final String COOKIE_TOKEN = "=";
    private static final int COOKIE_FIRST_IDX = 0;
    private static final int COOKIE_SECOND_IDX = 1;

    @Test
    void 로그인_후_user_list_페이지요청_cookie_session_검사() throws Exception {
        // 회원가입
        User user = new User("javajigi", "password", "jay", "jay@gmail.com");
        DataBase.addUser(user);
        HttpRequest httpRequest = HttpRequestHelper.createHttpRequest("src/test/java/data/user_login_request.txt");
        HttpResponse httpResponse = HttpResponse.of();
        ViewResolver viewResolver = new TemplateResourceResolver();
        Controller controller = new LoginController(viewResolver);
        controller.service(httpRequest, httpResponse);

        // uuid 로 생성한 session id 가져오기
        String sessionId = getRandomSessionId(httpResponse);
        HttpRequest indexPageRequest = createHttpRequestWithSessionId(sessionId);
        HttpResponse indexHttpResponse = HttpResponse.of();
        // session id 로 user list 페이지 요청
        Controller indexController = new UserListController(new HandlebarResourceResolver());
        indexController.service(indexPageRequest, indexHttpResponse);

        assertThat(indexHttpResponse.getPath()).isEqualTo("/user/list.html");
        assertThat(indexHttpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.OK.getValue());
    }

    private String getRandomSessionId(HttpResponse httpResponse) {
        String[] split = httpResponse.getHeaders("Set-Cookie").split("\r\n");
        String[] cookies = split[COOKIE_FIRST_IDX].split(COOKIE_DELIMITER);
        String[] session = cookies[COOKIE_FIRST_IDX].split(COOKIE_TOKEN);

        return session[COOKIE_SECOND_IDX];
    }

    private HttpRequest createHttpRequestWithSessionId(String sessionId) {
        String method = "GET";
        String path = "/index.html";
        String version = "HTTP/1.1";
        RequestLine requestLine = RequestLine.from(method, path, version);
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Host", "localhost:8080");
        requestHeaders.put("Connection", "keep-alive");
        requestHeaders.put("Accept", "*/*");
        requestHeaders.put("Cookie", "session=" + sessionId + "; logined=true"); // uuid 로 생성한 sessionId를 가져온다.
        RequestHeaderParams requestHeaderParams = RequestHeaderParams.of(requestHeaders);
        RequestHeader requestHeader = RequestHeader.of(requestHeaderParams);

        return new HttpRequest.Builder()
                .requestHeader(requestHeader).requestLine(requestLine)
                .build();
    }

    @Test
    void 로그인하지않고_user_list_페이지_요청_login_페이지로_리다이렉트() throws Exception {
        HttpRequest httpRequest = HttpRequestHelper.createHttpRequest("src/test/java/data/user_not_logged_in_user_list_request.txt");
        HttpResponse httpResponse = HttpResponse.of();
        ViewResolver viewResolver = new HandlebarResourceResolver();

        Controller controller = new UserListController(viewResolver);
        controller.service(httpRequest, httpResponse);

        assertThat(httpResponse.getPath()).isEqualTo("/user/login.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.REDIRECT.getValue());
    }
}