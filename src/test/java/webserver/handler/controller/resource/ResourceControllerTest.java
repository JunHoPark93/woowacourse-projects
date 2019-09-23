package webserver.handler.controller.resource;

import org.junit.jupiter.api.Test;
import webserver.handler.controller.Controller;
import webserver.handler.controller.HttpRequestHelper;
import webserver.http.HttpStatus;
import webserver.http.MediaType;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.view.StaticResourceResolver;
import webserver.view.ViewLocation;
import webserver.view.ViewResolver;

import static org.assertj.core.api.Assertions.assertThat;

class ResourceControllerTest {
    @Test
    void 정적_자원_요청() throws Exception {
        HttpRequest httpRequest = HttpRequestHelper.createHttpRequest("src/test/java/data/css_request.txt");
        HttpResponse httpResponse = HttpResponse.of();
        ViewResolver viewResolver = new StaticResourceResolver();

        Controller controller = new ResourceController(viewResolver);
        controller.service(httpRequest, httpResponse);

        assertThat(httpResponse.getPath()).isEqualTo(ViewLocation.STATIC.getLocation() + "/css/styles.css");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.OK.getValue());
        assertThat(httpResponse.getMediaType()).isEqualTo(MediaType.CSS.getContentType());
    }
}