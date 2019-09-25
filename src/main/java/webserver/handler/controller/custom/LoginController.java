package webserver.handler.controller.custom;

import db.DataBase;
import model.User;
import webserver.handler.controller.AbstractController;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;
import webserver.http.session.HttpSession;
import webserver.http.session.SessionContextHolder;
import webserver.view.ViewResolver;

import java.util.UUID;

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

        HttpSession httpSession = HttpSession.newInstance();
        String sessionId = createRandomId();
        httpSession.setAttribute("user", user);
        bindToContext(httpSession, sessionId);

        createResponse(response, sessionId);
    }

    private void validUser(String password, User user) {
        if (user == null) {
            throw new RuntimeException("user not exists");
        }

        if (!user.isPasswordMatch(password)) {
            throw new RuntimeException("user password mismatch");
        }
    }

    private String createRandomId() {
        return UUID.randomUUID().toString();
    }

    private void bindToContext(HttpSession httpSession, String sessionId) {
        SessionContextHolder.bind(sessionId, httpSession);
    }

    private void createResponse(HttpResponse response, String sessionId) throws Exception {
        response.addCookie("session", sessionId);
        response.setCookieHttpOnly("session");
        response.addCookieValues("session", "Path=/");
        response.redirect(viewResolver.resolve("/index.html"));
    }

    @Override
    protected String getRequestedMapping() {
        return MAPPING_PATH;
    }
}
