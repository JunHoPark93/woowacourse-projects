package webserver.handler.controller.custom;

import webserver.handler.controller.AbstractController;
import webserver.http.MediaType;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.http.response.ResponseHeader;
import webserver.view.ViewResolver;

import java.util.HashMap;
import java.util.Map;

public class HomeController extends AbstractController {
    private static final String MAPPING_PATH = "/";

    public HomeController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) throws Exception {
        response.ok(viewResolver.resolve("/index.html"), new ResponseHeader(setHeaders()));
    }

    private Map<String, String> setHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", MediaType.find("/index.html").getContentType());
        return headers;
    }

    @Override
    protected String getRequestedMapping() {
        return MAPPING_PATH;
    }
}
