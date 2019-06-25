package chess.controller;

import chess.domain.board.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

import java.util.Set;

public class MovableController {

    public static Object init(Request request, Response response) {
        Game game = request.session().attribute("game");
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement =jsonParser.parse(request.body());
        String src = jsonElement.getAsJsonObject().get("src").getAsString();

        Square square = new Square(new XPosition(src.substring(0, 1)),
                new YPosition(src.substring(1, 2)));

        Set<Vector> set = game.moveList(square);

        request.session().attribute("game", game);
        return new Gson().toJson(set);
    }
}
