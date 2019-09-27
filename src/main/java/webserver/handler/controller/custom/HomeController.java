package webserver.handler.controller.custom;

import webserver.handler.controller.AbstractController;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.view.ViewResolver;

public class HomeController extends AbstractController {
    private static final String MAPPING_PATH = "/";

    public HomeController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        response.ok(viewResolver.resolve("/index.html"));
    }

    @Override
    protected String getRequestedMapping() {
        return MAPPING_PATH;
    }
}
