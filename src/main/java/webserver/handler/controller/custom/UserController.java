package webserver.handler.controller.custom;

import db.DataBase;
import model.User;
import webserver.handler.controller.AbstractController;
import webserver.http.MediaType;
import webserver.http.request.HttpRequest;
import webserver.http.request.QueryParams;
import webserver.http.response.HttpResponse;
import webserver.http.response.ResponseHeader;
import webserver.view.ViewResolver;

import java.util.HashMap;
import java.util.Map;

public class UserController extends AbstractController {
    private static final String MAPPING_PATH = "/user/create";
    private static final String SLASH = "/";

    public UserController(ViewResolver viewResolver) {
        super(viewResolver);
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) throws Exception {
        QueryParams body = request.getBody();
        User user = new User(body.get("userId"), body.get("password"),
                body.get("name"), body.get("email"));
        DataBase.addUser(user);

        response.redirect(viewResolver.resolve("/index.html"), new ResponseHeader(setHeaders()));
    }

    private Map<String, String> setHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", MediaType.find("/index.html").getContentType());
        headers.put("Location", "/index.html" + "\r\n");
        return headers;
    }

    @Override
    protected String getRequestedMapping() {
        return MAPPING_PATH;
    }
}
