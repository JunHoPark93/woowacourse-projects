package chess.domain.piece;

import chess.domain.path.Path;

public class Knight extends Piece {
    private static final double SCORE = 2.5;

    public Knight(PieceColor color, Path path) {
        super(color, path, PieceType.KNIGHT);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
