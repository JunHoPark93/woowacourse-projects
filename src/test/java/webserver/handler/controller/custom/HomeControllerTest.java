package webserver.handler.controller.custom;

import org.junit.jupiter.api.Test;
import webserver.handler.controller.Controller;
import webserver.handler.controller.HttpRequestHelper;
import webserver.handler.controller.resource.TemplateController;
import webserver.http.HttpStatus;
import webserver.http.MediaType;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.view.TemplateResourceResolver;
import webserver.view.ViewResolver;

import static org.assertj.core.api.Assertions.assertThat;

class HomeControllerTest {
    @Test
    void 메인페이지_요청() throws Exception {
        HttpRequest httpRequest = HttpRequestHelper.createHttpRequest("src/test/java/data/index_request.txt");
        HttpResponse httpResponse = HttpResponse.of();
        ViewResolver viewResolver = new TemplateResourceResolver();

        Controller controller = new TemplateController(viewResolver);
        controller.service(httpRequest, httpResponse);

        assertThat(httpResponse.getPath()).isEqualTo("/index.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.OK.getValue());
        assertThat(httpResponse.getHeaders("Content-Type")).isEqualTo(MediaType.HTML.getContentType());
    }


}