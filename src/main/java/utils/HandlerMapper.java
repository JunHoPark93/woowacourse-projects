package utils;

import controller.Controller;
import controller.HomeController;
import controller.UserController;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapper {
    private static final Map<String, Controller> mapper = new HashMap<>();

    static {
        mapper.put("/", new HomeController());
        mapper.put("/user", new UserController());
    }

    public static Controller findController(String url) {
        return mapper.get(url);
    }
}
