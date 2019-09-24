package webserver.handler.controller.resource;

import webserver.handler.controller.AbstractController;
import webserver.http.MediaType;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.http.response.ResponseHeader;
import webserver.view.ViewResolver;

import java.util.HashMap;
import java.util.Map;

public class ResourceController extends AbstractController {
    private static final String RESOURCE_MAPPING_PATH_DELIMITER = ".";

    public ResourceController(ViewResolver viewResolver) {
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
        return request.getPath().contains(RESOURCE_MAPPING_PATH_DELIMITER) && !request.getPath().endsWith(".html");
    }

    @Override
    protected String getRequestedMapping() {
        return RESOURCE_MAPPING_PATH_DELIMITER;
    }
}
