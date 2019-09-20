package webserver.handler.strategy;

import webserver.handler.Handler;
import webserver.handler.StaticResourcePath;
import webserver.http.HttpStatus;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.view.ModelAndView;
import webserver.view.View;
import webserver.view.ViewLocation;

public class StaticResourceMappingHandler implements Handler {

    @Override
    public ModelAndView handle(HttpRequest httpRequest, HttpResponse httpResponse) {
        View view = new View(httpRequest.getPath().substring(1), ViewLocation.STATIC);
        httpResponse.forward(httpRequest.getResource(), HttpStatus.OK);

        return new ModelAndView(view);
    }

    @Override
    public Handler getHandler(String filePath) {
        if (StaticResourcePath.contains(filePath)) {
            return this;
        }
        return null;
    }
}
