package chess;

import chess.domain.board.Game;
import spark.Request;
import spark.Response;
import spark.Route;

public interface CustomRoute extends Route  {
    Object handle(Request request, Response response, Game game) throws Exception;
}
