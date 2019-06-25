package chess.domain.dto;

import chess.domain.board.Player;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.List;

public class BoardDto {
    private Player whitePlayer;
    private Player blackPlayer;
    private PieceColor turn;
    private List<Piece> whiteDead;
    private List<Piece> blackDead;

    public BoardDto(Player whitePlayer, Player blackPlayer, PieceColor turn, List<Piece> whiteDead, List<Piece> blackDead) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.turn = turn;
        this.whiteDead = whiteDead;
        this.blackDead = blackDead;
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

    public List<Piece> getWhiteDead() {
        return whiteDead;
    }

    public List<Piece> getBlackDead() {
        return blackDead;
    }
}
