package webserver.handler.controller;

import webserver.exception.DefaultControllerException;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.view.ViewResolver;

public class DefaultController extends AbstractController {

    public DefaultController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        response.notFound(viewResolver.resolve("/error.html"));
    }

    @Override
    public boolean isMapping(HttpRequest request) {
        return true;
    }

    @Override
    protected String getRequestedMapping() {
        throw new DefaultControllerException("Default 컨트롤러는 mapping 이 존재하지 않습니다");
    }
}
