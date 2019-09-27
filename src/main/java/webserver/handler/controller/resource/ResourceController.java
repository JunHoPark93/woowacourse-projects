package webserver.handler.controller.resource;

import webserver.exception.ResourceNotFoundException;
import webserver.handler.controller.AbstractController;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.view.ViewResolver;

public class ResourceController extends AbstractController {
    private static final String RESOURCE_MAPPING_PATH_DELIMITER = ".";

    public ResourceController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        try {
            response.ok(viewResolver.resolve(request.getPath()));
        } catch (ResourceNotFoundException e) {
            response.notFound(request.getPath());
        }
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
