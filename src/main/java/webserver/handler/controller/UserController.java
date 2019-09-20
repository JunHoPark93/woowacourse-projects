package webserver.handler.controller;

import db.DataBase;
import model.User;
import webserver.http.HttpStatus;
import webserver.http.request.HttpRequest;
import webserver.http.request.QueryParams;
import webserver.http.response.HttpResponse;

public class UserController extends AbstractController {
    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        if ("/user/form.html".equals(request.getPath())) {
            request.getResource();
            response.sendRedirect("user/" + request.getResource(), HttpStatus.OK);
        }
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        if ("/user/create".equals(request.getPath())) {
            QueryParams body = request.getBody();
            User user = new User(body.get("userId"), body.get("password")
                    , body.get("name"), body.get("email"));
            DataBase.addUser(user);
            response.sendRedirect("/index.html", HttpStatus.REDIRECT);
        }
    }
}
