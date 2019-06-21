package chess.domain.piece;

import chess.domain.path.Path;

public class Queen extends Piece {
    private static final double SCORE = 9;

    public Queen(PieceColor color, Path path) {
        super(color, path);
    }


    @Override
    public double getScore() {
        return SCORE;
    }
}
