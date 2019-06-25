package chess.controller;

import chess.WebUIChessApplication;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class MainController {
    public static Object init(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return WebUIChessApplication.render(model, "index.html");
    }
}
