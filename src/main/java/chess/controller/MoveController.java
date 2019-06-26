package chess.controller;

import chess.dao.ChessDao;
import chess.domain.board.Game;
import chess.domain.board.Square;
import chess.domain.dto.HistoryDto;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MoveController {
    public static Object init(Request request, Response response) throws SQLException {
        Game game = request.session().attribute("game");
        HistoryDto historyDto = new Gson().fromJson(request.body(), HistoryDto.class);
        Square source = historyDto.getSrc();
        Square target = historyDto.getTrg();
        Map<String, Object> model = new HashMap<>();

        int round = request.session().attribute("round");
        ChessDao.insertRound(round, source, target);

        boolean kingAlive = game.move(source, target);
        request.session().attribute("game", game);

        if (!kingAlive) {
            return kingDead(game, model, round);
        }

        return new Gson().toJson(target);
    }

    private static Object kingDead(Game game, Map<String, Object> model, int round) throws SQLException {
        model.put("turn", game.getTurn());
        ChessDao.endRound(round);
        return new Gson().toJson(model);
    }
}
