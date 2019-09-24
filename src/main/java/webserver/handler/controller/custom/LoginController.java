package webserver.handler.controller.custom;

import db.DataBase;
import model.User;
import webserver.handler.controller.AbstractController;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.view.ViewResolver;

public class LoginController extends AbstractController {
    private static final String MAPPING_PATH = "/user/login";

    public LoginController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) throws Exception {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        User user = DataBase.findUserById(userId);
        validUser(password, user);

        response.addCookie("logined", "true");
        response.addCookie("Path", "/");
        response.redirect(viewResolver.resolve("/index.html"));
    }

    private void validUser(String password, User user) {
        if (user == null) {
            throw new RuntimeException("user not exists");
        }

        if (!user.isPasswordMatch(password)) {
            throw new RuntimeException("user password mismatch");
        }
    }

    @Override
    protected String getRequestedMapping() {
        return MAPPING_PATH;
    }
}
