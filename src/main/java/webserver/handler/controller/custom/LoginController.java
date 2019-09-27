package webserver.handler.controller.custom;

import db.DataBase;
import model.User;
import webserver.handler.controller.AbstractController;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.http.session.HttpSession;
import webserver.http.session.SessionContextHolder;
import webserver.view.ViewResolver;

public class LoginController extends AbstractController {
    private static final String MAPPING_PATH = "/user/login";

    public LoginController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        DataBase.findUserById(userId)
                .filter(user -> user.isPasswordMatch(password))
                .map(user -> {
                    HttpSession session = setSession(request, user);
                    createLogInSuccessResponse(response, session.getId());
                    return true;
                }).orElseGet(() -> {
                    createLoginFailResponse(response);
                    return false;
                });
    }

    private HttpSession setSession(HttpRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        bindToContext(session);
        return session;
    }

    private void bindToContext(HttpSession session) {
        SessionContextHolder.bind(session.getId(), session);
    }

    private void createLogInSuccessResponse(HttpResponse response, String sessionId) {
        response.addCookie("session", sessionId);
        response.setCookieHttpOnly("session");
        response.addCookieValues("session", "Path=/");

        response.addCookie("logined", "true");
        response.addCookieValues("logined", "Path=/");

        response.redirect(viewResolver.resolve("/index.html"));
    }

    private void createLoginFailResponse(HttpResponse response) {
        response.addCookie("logined", "false");
        response.redirect(viewResolver.resolve("/user/login_failed.html"));
    }

    @Override
    protected String getRequestedMapping() {
        return MAPPING_PATH;
    }
}
