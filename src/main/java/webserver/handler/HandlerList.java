package webserver.handler;

import webserver.handler.strategy.ControllerMappingHandler;
import webserver.handler.strategy.StaticResourceMappingHandler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class HandlerList {
    private static final Handler[] HANDLERS = {
            new ControllerMappingHandler(),
            new StaticResourceMappingHandler()
    };

    public static List<Handler> LIST = Collections.unmodifiableList(Arrays.asList(HANDLERS));
}
