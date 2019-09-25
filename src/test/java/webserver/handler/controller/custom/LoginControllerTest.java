package webserver.handler.controller.custom;

import db.DataBase;
import model.User;
import org.junit.jupiter.api.Test;
import webserver.handler.controller.Controller;
import webserver.handler.controller.HttpRequestHelper;
import webserver.http.HttpStatus;
import webserver.http.MediaType;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.view.TemplateResourceResolver;
import webserver.view.ViewResolver;

import static org.assertj.core.api.Assertions.assertThat;

class LoginControllerTest {
    private static final int LENGTH_OF_UUID = 36;
    private static final String COOKIE_DELIMITER = "; ";
    private static final int COOKIE_FIRST_IDX = 0;
    private static final int COOKIE_SECOND_IDX = 1;
    private static final int COOKIE_THIRD_IDX = 2;

    @Test
    void 로그인_성공() throws Exception {
        User user = new User("javajigi", "password", "jay", "jay@gmail.com");
        DataBase.addUser(user);
        HttpRequest httpRequest = HttpRequestHelper.createHttpRequest("src/test/java/data/user_login_request.txt");
        HttpResponse httpResponse = HttpResponse.of();
        ViewResolver viewResolver = new TemplateResourceResolver();

        Controller controller = new LoginController(viewResolver);
        controller.service(httpRequest, httpResponse);

        String[] cookies = httpResponse.getHeaders("Set-Cookie").split("\r\n");

        // 로그인이 성공하면 메인 페이지로 redirect 되어야 한다
        assertThat(httpResponse.getPath()).isEqualTo("/index.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.REDIRECT.getValue());
        assertThat(httpResponse.getHeaders("Content-Type")).isEqualTo(MediaType.HTML.getContentType());

        // cookie 를 검증한다. 세션이 javascript 단에서 수정되면 안되기 때문에 항상 HttpOnly 로 설정된다.
        for (String cookie : cookies) {
            String value = cookie.substring(cookie.indexOf("=") + 1);
            String[] split = value.split(COOKIE_DELIMITER);
            assertThat(split[COOKIE_FIRST_IDX].length()).isEqualTo(LENGTH_OF_UUID);
            assertThat(split[COOKIE_SECOND_IDX]).isEqualTo("HttpOnly");
            assertThat(split[COOKIE_THIRD_IDX]).isEqualTo("Path=/");
        }
    }
}
