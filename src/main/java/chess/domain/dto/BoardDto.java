package chess.domain.dto;

import chess.domain.board.Player;
import chess.domain.piece.PieceColor;
import com.google.gson.Gson;

public class BoardDto {
    private Player whitePlayer;
    private Player blackPlayer;
    private PieceColor turn;

    public BoardDto(Player whitePlayer, Player blackPlayer, PieceColor turn) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.turn = turn;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public PieceColor getTurn() {
        return turn;
    }
}
