package chess.domain.piece;

import chess.domain.path.Path;

public class King extends Piece {
    private static final double SCORE = 0;

    public King(PieceColor color, Path path) {
        super(color, path);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
