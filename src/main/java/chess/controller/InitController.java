package chess.controller;

import chess.dao.ChessDao;
import chess.domain.board.DefaultPlayer;
import chess.domain.board.Game;
import chess.domain.board.Player;
import chess.domain.board.PlayerFactory;
import chess.domain.dto.BoardDto;
import chess.domain.dto.HistoryDto;
import chess.domain.piece.PieceColor;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.sql.SQLException;
import java.util.List;

public class InitController {
    public static Object init(Request request, Response response) throws SQLException {
        Player whitePlayer = new DefaultPlayer(PlayerFactory.init(PieceColor.WHITE));
        Player blackPlayer = new DefaultPlayer(PlayerFactory.init(PieceColor.BLACK));
        Game game = new Game(whitePlayer, blackPlayer);

        boolean keepPlaying = ChessDao.notEnd();
        if (keepPlaying) {
            return resume(request, whitePlayer, blackPlayer, game);
        }

        return newGame(request, whitePlayer, blackPlayer, game);
    }

    private static Object resume(Request request, Player whitePlayer, Player blackPlayer, Game game) throws SQLException {
        int round = ChessDao.getRound();
        request.session().attribute("round", round);
        List<HistoryDto> list = ChessDao.getHistory(round);

        for (HistoryDto dto : list) {
            game.move(dto.getSrc(), dto.getTrg());
        }

        BoardDto boardDto = new BoardDto(whitePlayer, blackPlayer, game.getTurn(),
                game.getDeadList(PieceColor.WHITE), game.getDeadList(PieceColor.BLACK));

        request.session().attribute("game", game);
        return new Gson().toJson(boardDto);
    }

    private static Object newGame(Request request, Player whitePlayer, Player blackPlayer, Game game) throws SQLException {
        int round = ChessDao.getRound() + 1;
        request.session().attribute("round", round);

        ChessDao.addRound(round);

        BoardDto boardDto = new BoardDto(whitePlayer, blackPlayer, game.getTurn(),
                game.getDeadList(PieceColor.WHITE), game.getDeadList(PieceColor.BLACK));

        request.session().attribute("game", game);
        return new Gson().toJson(boardDto);
    }
}
