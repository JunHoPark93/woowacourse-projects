package chess.controller;

import chess.WebUIChessApplication;
import chess.dao.ChessDao;
import chess.domain.board.Game;
import chess.domain.board.Score;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StatusController {
    public static Object init(Request request, Response response) throws SQLException {
        Game game = request.session().attribute("game");
        Map<String, Object> model = new HashMap<>();
        Score score = game.score();

        int round = request.session().attribute("round");
        ChessDao.endRound(round);

        model.put("blackScore" , score.getBlackScore());
        model.put("whiteScore" , score.getWhiteScore());
        model.put("loser", score.getLoser());

        return WebUIChessApplication.render(model, "result.html");
    }
}
