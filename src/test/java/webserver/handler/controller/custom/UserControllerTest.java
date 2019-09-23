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
import webserver.view.ViewLocation;
import webserver.view.ViewResolver;

import static org.assertj.core.api.Assertions.assertThat;

class UserControllerTest {
    @Test
    void 회원가입_페이지_요청() throws Exception {
        HttpRequest httpRequest = HttpRequestHelper.createHttpRequest("src/test/java/data/user_form_request.txt");
        HttpResponse httpResponse = HttpResponse.of();
        ViewResolver viewResolver = new TemplateResourceResolver();

        Controller controller = new TemplateController(viewResolver);
        controller.service(httpRequest, httpResponse);

        assertThat(httpResponse.getPath()).isEqualTo(ViewLocation.TEMPLATE.getLocation() + "/user/form.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.OK.getValue());
        assertThat(httpResponse.getMediaType()).isEqualTo(MediaType.HTML.getContentType());
    }

    @Test
    void 회원가입_성공() throws Exception {
        HttpRequest httpRequest = HttpRequestHelper.createHttpRequest("src/test/java/data/user_form_post_request.txt");
        HttpResponse httpResponse = HttpResponse.of();
        ViewResolver viewResolver = new TemplateResourceResolver();

        Controller controller = new UserController(viewResolver);
        controller.service(httpRequest, httpResponse);

        // 회원 가입이 성공하면 메인 페이지로 redirect 되어야 한다
        assertThat(httpResponse.getPath()).isEqualTo(ViewLocation.TEMPLATE.getLocation() + "/index.html");
        assertThat(httpResponse.getHttpStatusCode()).isEqualTo(HttpStatus.REDIRECT.getValue());
        assertThat(httpResponse.getMediaType()).isEqualTo(MediaType.HTML.getContentType());
    }
}
