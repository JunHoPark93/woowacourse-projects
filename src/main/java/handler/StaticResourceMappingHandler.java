package handler;

import model.http.HttpRequest;
import model.http.HttpResponse;
import model.http.ModelAndView;
import model.http.View;
import utils.HttpStatus;

public class StaticResourceMappingHandler implements Handler {

    @Override
    public ModelAndView handle(HttpRequest httpRequest, HttpResponse httpResponse) {
        View view = new View(httpRequest.getPath().substring(1));
        httpResponse.forward(httpRequest.getResource(), HttpStatus.OK);
        return new ModelAndView(view, true);
    }

    @Override
    public Handler getHandler(String filePath) {
        if (StaticResourcePath.contains(filePath)) {
            return this;
        }
        return null;
    }
}
