package controller;

import model.http.HttpRequest;
import model.http.HttpResponse;

public class UserController extends AbstractController{

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        request.getResource();
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
    }
}
