package chess.controller;

import chess.dao.ChessDao;
import chess.domain.board.Game;
import chess.domain.board.Square;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MoveController {
    public static Object init(Request request, Response response) throws SQLException {
        Game game = request.session().attribute("game");
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement =jsonParser.parse(request.body());
        String src = jsonElement.getAsJsonObject().get("src").getAsString();
        String trg = jsonElement.getAsJsonObject().get("trg").getAsString();

        Square source = new Square(new XPosition(src.substring(0, 1)),
                new YPosition(src.substring(1, 2)));

        Square target = new Square(new XPosition(trg.substring(0, 1)),
                new YPosition(trg.substring(1, 2)));

        Map<String, Object> model = new HashMap<>();

        boolean playing = game.move(source, target);

        int round = request.session().attribute("round");
        ChessDao.insertRound(round, source, target);

        request.session().attribute("game", game);

        if (!playing) { // 왕이 죽은 경우
            model.put("turn", game.getTurn());
            ChessDao.endRound(round);
            return new Gson().toJson(model);
        }

        return new Gson().toJson(target);
    }
}
