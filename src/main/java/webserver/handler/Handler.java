package webserver.handler;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.view.ModelAndView;

public interface Handler {
    ModelAndView handle(HttpRequest httpRequest, HttpResponse httpResponse);

    Handler getHandler(String path);
}
