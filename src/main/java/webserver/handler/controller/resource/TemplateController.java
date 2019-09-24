package webserver.handler.controller.resource;

import webserver.handler.controller.AbstractController;
import webserver.http.MediaType;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.http.response.ResponseHeader;
import webserver.view.ViewResolver;

import java.util.HashMap;
import java.util.Map;

public class TemplateController extends AbstractController {
    private static final String TEMPLATE_MAPPING_ENDING_PATH = ".html";

    public TemplateController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) throws Exception {
        response.ok(viewResolver.resolve(request.getPath()), new ResponseHeader(setHeaders(request)));
    }

    private Map<String, String> setHeaders(HttpRequest request) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", MediaType.find(request.getPath()).getContentType());
        return headers;
    }

    @Override
    public boolean isMapping(HttpRequest request) {
        return request.getPath().endsWith(TEMPLATE_MAPPING_ENDING_PATH);
    }

    @Override
    protected String getRequestedMapping() {
        return TEMPLATE_MAPPING_ENDING_PATH;
    }
}
