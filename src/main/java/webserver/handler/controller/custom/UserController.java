package webserver.handler.controller.custom;

import db.DataBase;
import model.User;
import webserver.handler.controller.AbstractController;
import webserver.http.request.HttpRequest;
import webserver.http.request.QueryParams;
import webserver.http.response.HttpResponse;
import webserver.view.ViewResolver;

public class UserController extends AbstractController {
    private static final String MAPPING_PATH = "/user/create";
    private static final String SLASH = "/";

    public UserController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) throws Exception {
        User user = new User(request.getParameter("userId"), request.getParameter("password"),
                request.getParameter("name"), request.getParameter("email"));
        DataBase.addUser(user);

        response.redirect(viewResolver.resolve("/index.html"));
    }

    @Override
    protected String getRequestedMapping() {
        return MAPPING_PATH;
    }
}
