package controller;

import model.http.HttpRequest;
import model.http.HttpResponse;
import utils.HttpStatus;

public class UserController extends AbstractController {

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        request.getResource();
        response.sendRedirect("user/" + request.getResource(), HttpStatus.OK);
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
    }
}
