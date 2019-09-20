package webserver.handler.controller;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public interface Controller {
    void service(HttpRequest request, HttpResponse response);
}
