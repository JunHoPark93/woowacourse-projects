package controller;

import db.DataBase;
import model.User;
import model.http.HttpRequest;
import model.http.HttpResponse;
import utils.HttpStatus;

import java.util.Map;

public class UserController extends AbstractController {

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {

        if ("/user/form.html".equals(request.getPath())) {
            request.getResource();
            response.sendRedirect("user/" + request.getResource(), HttpStatus.OK);
            return;
        }

        if ("/user/create".equals(request.getPath())) {
            Map<String, String> queryParams = request.getQueryParams();
            User user = new User(queryParams.get("userId"), queryParams.get("password")
                    , queryParams.get("name"), queryParams.get("email"));
            DataBase.addUser(user);
            response.sendRedirect("/index.html", HttpStatus.REDIRECT);
        }
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
    }
}
