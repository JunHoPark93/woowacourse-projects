package chess.controller;

import chess.domain.board.*;
import chess.domain.dto.MovableDto;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.Set;

public class MovableController {
    public static Object init(Request request, Response response) {
        Game game = request.session().attribute("game");

        MovableDto movableDto = new Gson().fromJson(request.body(), MovableDto.class);
        Set<Vector> set = game.moveList(movableDto.getSrc());

        request.session().attribute("game", game);
        return new Gson().toJson(set);
    }
}
