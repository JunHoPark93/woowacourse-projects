package webserver.handler.controller.custom;

import db.DataBase;
import model.User;
import org.junit.jupiter.api.Test;
import webserver.handler.controller.Controller;
import webserver.handler.controller.HttpRequestHelper;
import webserver.http.HttpStatus;
import webserver.http.MediaType;
import webserver.http.request.HttpRequest;
import webserver.http.response.Cookie;
import webserver.http.response.HttpResponse;
import webserver.view.TemplateResourceResolver;
import webserver.view.ViewLocation;
import webserver.view.ViewResolver;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginControllerTest {
    @Test
    void 로그인_성공() throws Exception {
        User user = new User("javajigi", "password", "jay", "jay@gmail.com");
        DataBase.addUser(user);
        HttpRequest httpRequest = HttpRequestHelper.createHttpRequest("src/test/java/data/user_login_request.txt");
        HttpResponse httpResponse = HttpResponse.of();
        ViewResolver viewResolver = new TemplateResourceResolver();

        Controller controller = new LoginController(viewResolver);
        controller.service(httpRequest, httpResponse);

        // 로그인이 성공하면 메인 페이지로 redirect 되어야 한다
        assertThat(httpResponse.getPath()).isEqualTo(ViewLocation.TEMPLATE.getLocation() + "/index.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.REDIRECT.getValue());
        assertThat(httpResponse.getHeaders("Content-Type")).isEqualTo(MediaType.HTML.getContentType());
        assertThat(httpResponse.getHeaders("Set-Cookie")).isEqualTo("logined=true; Path=/");
        assertThat(httpResponse.getHeaders("Set-Cookie")).isEqualTo("logined=true; Path=/");
    }
}
