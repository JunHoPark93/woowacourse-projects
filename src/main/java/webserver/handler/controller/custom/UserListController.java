package webserver.handler.controller.custom;

import webserver.handler.controller.AbstractController;
import webserver.http.request.HttpRequest;
import webserver.http.response.Cookie;
import webserver.http.response.HttpResponse;
import webserver.view.ViewResolver;

public class UserListController extends AbstractController {
    private static final String MAPPING_PATH = "/user/list.html";

    public UserListController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) throws Exception {
        Cookie cookie = request.getCookie();
        if (cookie.get("logined").equals("true")) {
            response.ok(viewResolver.resolve("/user/list.html"));
            return;
        }
        response.redirect(viewResolver.resolve("/user/login.html"));
    }

    @Override
    protected String getRequestedMapping() {
        return MAPPING_PATH;
    }
}
