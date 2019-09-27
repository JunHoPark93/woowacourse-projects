package webserver.handler.controller.resource;

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

class TemplateControllerTest {
    @Test
    void html_파일_요청() throws Exception {
        HttpRequest httpRequest = HttpRequestHelper.createHttpRequest("src/test/java/data/html_request.txt");
        HttpResponse httpResponse = HttpResponse.of();
        ViewResolver viewResolver = new TemplateResourceResolver();

        Controller controller = new TemplateController(viewResolver);
        controller.service(httpRequest, httpResponse);

        assertThat(httpResponse.getPath()).isEqualTo("/user/form.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.OK.getValue());
        assertThat(httpResponse.getHeaders("Content-Type")).isEqualTo(MediaType.HTML.getContentType());
    }

    @Test
    void 유효하지_않은_html_요청_404() throws Exception {
        HttpRequest httpRequest = HttpRequestHelper.createHttpRequest("src/test/java/data/html_404_expected_request.txt");
        HttpResponse httpResponse = HttpResponse.of();
        ViewResolver viewResolver = new TemplateResourceResolver();

        Controller controller = new TemplateController(viewResolver);
        controller.service(httpRequest, httpResponse);

        assertThat(httpResponse.getPath()).isEqualTo("/error.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.NOT_FOUND.getValue());
    }
}