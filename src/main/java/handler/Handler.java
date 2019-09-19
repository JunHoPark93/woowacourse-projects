package handler;

import model.http.HttpRequest;
import model.http.HttpResponse;
import model.http.ModelAndView;

public interface Handler {
    ModelAndView handle(HttpRequest httpRequest, HttpResponse httpResponse);

    Handler getHandler(String path);
}
