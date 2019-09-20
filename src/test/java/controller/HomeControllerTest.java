package controller;

import org.junit.jupiter.api.Test;
import webserver.handler.controller.Controller;
import webserver.handler.controller.HomeController;
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

class HomeControllerTest {
    @Test
    void 메인_페이지_요청_컨트롤러() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/java/data/index_request.txt");
        InputStreamReader inputStream = new InputStreamReader(fileInputStream);
        HttpRequest httpRequest = RequestHeaderParser.parseRequest(inputStream);
        HttpResponse httpResponse = HttpResponse.of();

        Controller controller = new HomeController();
        controller.service(httpRequest, httpResponse);

        assertThat(httpResponse.getLocation()).isEqualTo("index.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.OK.getValue());
        assertThat(httpResponse.getMediaType()).isEqualTo(MediaType.HTML.getContentType());
    }
}
