package handler;

import controller.Controller;
import controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ControllerMappingHandlerTest {
    private Map<String, Controller> mapper = new HashMap<>();

    @BeforeEach
    void setUp() {
        mapper.put("/user", new UserController());
    }

    @Test
    void 요청에_맞는_컨트롤러_찾기() {
        Handler handler = new ControllerMappingHandler();
        handler.getHandler("/user");

        // TODO
    }
}