package webserver.handler.controller.custom;

import webserver.handler.controller.AbstractController;
import webserver.http.HttpStatus;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.view.ViewResolver;

public class HomeController extends AbstractController {
    private static final String MAPPING_PATH = "/";

    public HomeController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) throws Exception{
        response.send(viewResolver.resolve("/index.html"), HttpStatus.OK);
    }

    @Override
    protected String getRequestedMapping() {
        return MAPPING_PATH;
    }
}
