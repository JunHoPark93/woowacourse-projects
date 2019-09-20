package handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webserver.handler.controller.Controller;
import webserver.handler.controller.UserController;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerMappingHandlerTest {
    private Map<String, Controller> mapper = new HashMap<>();

    @BeforeEach
    void setUp() {
        // Handler 초기화 과정 흉내
        mapper.put("/user", new UserController());
    }

    @Test
    void 요청에_맞는_컨트롤러가_존재할때() {
        Object expectedHandler = mapper.get("/user");

        assertTrue(expectedHandler instanceof UserController);
    }

    @Test
    void 요청에_맞는_컨트롤러가_없을때() {
        Object expectedHandler = mapper.get("/unknown");

        assertNull(expectedHandler);
    }
}