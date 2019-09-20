package webserver.handler.controller;

import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.http.HttpStatus;

public class HomeController extends AbstractController {
    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        if ("index.html".equals(request.getResource())) {
            response.sendRedirect(request.getResource(), HttpStatus.OK);
        }
    }
}
