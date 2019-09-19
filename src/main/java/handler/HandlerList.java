package handler;

import java.util.ArrayList;
import java.util.List;

public class HandlerList {
    // TODO unmodifiable list로의 싱글턴
    public static List<Handler> LIST = new ArrayList<>();

    static {
        LIST.add(new ControllerMappingHandler());
        LIST.add(new StaticResourceMappingHandler());
    }

//    public Handler find(String dir) {
//        return
//    }
}
