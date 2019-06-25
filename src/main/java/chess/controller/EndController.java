package chess.controller;

import chess.WebUIChessApplication;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class EndController {
    public static Object init(Request request, Response response) {
        String loser = request.queryParams("loser");
        Map<String, Object> model = new HashMap<>();
        model.put("loser", loser);
        model.put("blackScore" , 0);
        model.put("whiteScore" , 0);
        return WebUIChessApplication.render(model, "result.html");
    }
}
