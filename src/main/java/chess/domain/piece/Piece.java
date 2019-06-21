package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.path.Path;

import java.util.Set;

public abstract class Piece {
    private final Path path;
    private PieceColor color;
    private boolean isMoved = false;

    public Piece(PieceColor color, Path path) {
        this.color = color;
        this.path = path;
    }

    public Set<Vector> movableList(Square source) {
        return path.movableList(source);
    }

    public abstract double getScore();

    public boolean isMoved() {
        return isMoved;
    }

    public PieceColor getColor() {
        return color;
    }
}
