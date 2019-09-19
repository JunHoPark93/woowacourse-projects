package controller;

import model.http.HttpRequest;
import model.http.HttpResponse;
import utils.HttpStatus;

public class HomeController extends AbstractController {
    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        request.getResource();
        response.sendRedirect(request.getResource(), HttpStatus.OK);
    }
}
