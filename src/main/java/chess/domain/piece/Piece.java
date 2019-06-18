package chess.domain.piece;

import chess.domain.board.Square;

import java.util.Set;

public abstract class Piece {
    private PieceColor color;
    private boolean isAlive = true;
    private boolean isMoved = false;

    public Piece(PieceColor color) {
        this.color = color;
    }

    public abstract Set<Square> movableList(Square source);

    public void dead() {
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public PieceColor getColor() {
        return color;
    }
}
