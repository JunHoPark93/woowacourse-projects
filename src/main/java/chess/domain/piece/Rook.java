package chess.domain.piece;

import chess.domain.path.Path;

public class Rook extends Piece {
    private static final double SCORE = 5;

    public Rook(PieceColor color, Path path) {
        super(color, path);
    }


    @Override
    public double getScore() {
        return SCORE;
    }
}
