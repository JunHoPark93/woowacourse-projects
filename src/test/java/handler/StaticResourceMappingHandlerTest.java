package handler;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class StaticResourceMappingHandlerTest {
    private static List<Handler> HANDLERS = new ArrayList<>();

    static {
        HANDLERS.add(new ControllerMappingHandler());
        HANDLERS.add(new StaticResourceMappingHandler());
    }

    @Test
    void css_자원_폴더에대한_요청핸들러() {
        Handler handler = getHandler("/css/");
        assertTrue(handler instanceof StaticResourceMappingHandler);
    }

    @Test
    void js_자원_폴더에대한_요청핸들러() {
        Handler handler = getHandler("/js/");
        assertTrue(handler instanceof StaticResourceMappingHandler);
    }

    @Test
    void font_자원_폴더에대한_요청핸들러() {
        Handler handler = getHandler("/fonts/");
        assertTrue(handler instanceof StaticResourceMappingHandler);
    }

    private Handler getHandler(String path) {
        for (Handler handler : HandlerList.LIST) {
            Handler targetHandler = handler.getHandler(path);
            if (targetHandler != null) {
                return targetHandler;
            }
        }
        return null;
    }
}