package webserver.handler;

import webserver.handler.controller.Controller;
import webserver.handler.controller.HomeController;
import webserver.handler.controller.UserController;

import java.util.HashMap;
import java.util.Map;

public final class HandlerMapper {
    private static final Map<String, Controller> mapper = new HashMap<>();

    static {
        mapper.put("/", new HomeController());
        mapper.put("/user", new UserController());
    }

    public static Controller findController(String url) {
        return mapper.get(url);
    }
}
