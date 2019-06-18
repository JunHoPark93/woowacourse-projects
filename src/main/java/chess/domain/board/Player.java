package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;

import java.util.Map;

public class Player {
    private final PieceColor color;
    private Map<Square, Piece> pieces;

    public Player(PieceColor color) {
        this.color = color;
        this.pieces = PlayerFactory.init(color);
    }

    public int getPiecesCount() {
        return pieces.size();
    }
}
