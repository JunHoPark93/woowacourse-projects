package controller;

import model.http.HttpRequest;
import model.http.HttpResponse;

public class HomeController extends AbstractController {
    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        request.getResource();
        response.sendRedirect(request.getResource());
    }
}
