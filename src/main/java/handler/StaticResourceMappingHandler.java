package handler;

import model.http.*;
import utils.HttpStatus;

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
