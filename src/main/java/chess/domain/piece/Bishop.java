package chess.domain.piece;

import chess.domain.path.Path;

public class Bishop extends Piece {
    private static final double SCORE = 3;

    public Bishop(PieceColor color, Path path) {
        super(color, path);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
