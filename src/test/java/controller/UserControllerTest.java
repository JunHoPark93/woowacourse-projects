package controller;

import org.junit.jupiter.api.Test;
import webserver.handler.controller.Controller;
import webserver.handler.controller.UserController;
import webserver.http.HttpStatus;
import webserver.http.MediaType;
import webserver.http.request.HttpRequest;
import webserver.http.request.RequestHeaderParser;
import webserver.http.response.HttpResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerTest {
    @Test
    void 회원가입_페이지_요청() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/java/data/user_form_request.txt");
        InputStreamReader inputStream = new InputStreamReader(fileInputStream);
        HttpRequest httpRequest = RequestHeaderParser.parseRequest(inputStream);
        HttpResponse httpResponse = HttpResponse.of();

        Controller controller = new UserController();
        controller.service(httpRequest, httpResponse);

        assertThat(httpResponse.getLocation()).isEqualTo("user/form.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.OK.getValue());
        assertThat(httpResponse.getMediaType()).isEqualTo(MediaType.HTML.getContentType());
    }

    @Test
    void 회원가입_성공() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/java/data/user_form_post_request.txt");
        InputStreamReader inputStream = new InputStreamReader(fileInputStream);
        HttpRequest httpRequest = RequestHeaderParser.parseRequest(inputStream);
        HttpResponse httpResponse = HttpResponse.of();

        Controller controller = new UserController();
        controller.service(httpRequest, httpResponse);

        // 회원 가입이 성공하면 메인 페이지로 redirect 되어야 한다
        assertThat(httpResponse.getLocation()).isEqualTo("/index.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.REDIRECT.getValue());
        assertThat(httpResponse.getMediaType()).isEqualTo(MediaType.HTML.getContentType());
    }
}
