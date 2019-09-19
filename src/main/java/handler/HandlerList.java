package handler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HandlerList {
    private static final Handler[] HANDLERS = {
            new ControllerMappingHandler(),
            new StaticResourceMappingHandler()
    };

    public static List<Handler> LIST = Collections.unmodifiableList(Arrays.asList(HANDLERS));
}
