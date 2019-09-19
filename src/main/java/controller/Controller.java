package controller;

import model.http.HttpRequest;
import model.http.HttpResponse;

public interface Controller {
    void service(HttpRequest request, HttpResponse response);
}
