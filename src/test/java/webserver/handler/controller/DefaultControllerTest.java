package webserver.handler.controller;

import org.junit.jupiter.api.Test;
import webserver.handler.controller.resource.TemplateController;
import webserver.http.HttpStatus;
import webserver.http.MediaType;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.view.TemplateResourceResolver;
import webserver.view.ViewResolver;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultControllerTest {

    @Test
    void 유효하지_않은_url_요청() throws Exception {
        HttpRequest httpRequest = HttpRequestHelper.createHttpRequest("src/test/java/data/url_404_expected_request.txt");
        HttpResponse httpResponse = HttpResponse.of();
        ViewResolver viewResolver = new TemplateResourceResolver();

        Controller controller = new TemplateController(viewResolver);
        controller.service(httpRequest, httpResponse);

        assertThat(httpResponse.getPath()).isEqualTo("/error.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.NOT_FOUND.getValue());
        assertThat(httpResponse.getHeaders("Content-Type")).isEqualTo(MediaType.HTML.getContentType());
    }
}
