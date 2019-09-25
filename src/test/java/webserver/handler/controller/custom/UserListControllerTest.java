package webserver.handler.controller.custom;

import org.junit.jupiter.api.Test;
import webserver.handler.controller.Controller;
import webserver.handler.controller.HttpRequestHelper;
import webserver.http.HttpStatus;
import webserver.http.request.HttpRequest;
import webserver.http.response.Cookie;
import webserver.http.response.HttpResponse;
import webserver.view.HandlebarResourceResolver;
import webserver.view.ViewResolver;

import static org.assertj.core.api.Assertions.assertThat;

class UserListControllerTest {
    @Test
    void 로그인_후_메인페이지요청_cookie_검사() throws Exception {
        HttpRequest httpRequest = HttpRequestHelper.createHttpRequest("src/test/java/data/user_logged_in_root_request.txt");
        HttpResponse httpResponse = HttpResponse.of();
        ViewResolver viewResolver = new HandlebarResourceResolver();

        Controller controller = new UserListController(viewResolver);
        controller.service(httpRequest, httpResponse);

        Cookie cookie = httpRequest.getCookie();
        assertThat(cookie.get("logined")).isEqualTo("true");

        assertThat(httpResponse.getPath()).isEqualTo("/user/list.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.OK.getValue());
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