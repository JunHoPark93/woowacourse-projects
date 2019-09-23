package webserver.handler.controller.custom;

import db.DataBase;
import model.User;
import webserver.handler.controller.AbstractController;
import webserver.http.HttpStatus;
import webserver.http.request.HttpRequest;
import webserver.http.request.QueryParams;
import webserver.http.response.HttpResponse;
import webserver.view.ViewResolver;

public class UserController extends AbstractController {
    private static final String MAPPING_PATH = "/user/create";

    public UserController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) throws Exception {
        QueryParams body = request.getBody();
        User user = new User(body.get("userId"), body.get("password"),
                body.get("name"), body.get("email"));
        DataBase.addUser(user);

        response.send(viewResolver.resolve("/index.html"), HttpStatus.REDIRECT);
    }

    @Override
    protected String getRequestedMapping() {
        return MAPPING_PATH;
    }
}
