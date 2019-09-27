package webserver.handler.controller.custom;

import db.DataBase;
import model.User;
import webserver.handler.controller.AbstractController;
import webserver.http.request.HttpRequest;
import webserver.http.response.Cookie;
import webserver.http.response.HttpResponse;
import webserver.view.ViewResolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserListController extends AbstractController {
    private static final String MAPPING_PATH = "/user/list.html";
    private static final String KEY_USERS = "users";
    private static final String LOG_IN_COOKIE = "logined";
    private static final String LOGIN_TRUE = "true";

    public UserListController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        Cookie cookie = request.getCookie();
        String loginCookie = cookie.get(LOG_IN_COOKIE);

        if (loginCookie.equals(LOGIN_TRUE)) {
            Map<String, Object> map = findAllUsers();
            response.ok(viewResolver.resolve("/user/list.html", map));
            return;
        }

        response.redirect(viewResolver.resolve("/user/login.html"));
    }

    private Map<String, Object> findAllUsers() {
        Collection<User> users = DataBase.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put(KEY_USERS, new ArrayList<>(users));

        return map;
    }

    @Override
    protected String getRequestedMapping() {
        return MAPPING_PATH;
    }
}
