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
