package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;

import java.util.Set;

public abstract class Piece {
    private PieceColor color;
    private PieceType type;

    public Piece(PieceColor color, PieceType type) {
        this.color = color;
        this.type = type;
    }

    public abstract double getScore();

    public abstract Set<Vector> movableList(Square source);

    public boolean isWhite() {
        return color.equals(PieceColor.WHITE);
    }

    public boolean isBlack() {
        return color.equals(PieceColor.BLACK);
    }

    public boolean isSameColor(PieceColor color) {
        return this.color.equals(color);
    }
}
