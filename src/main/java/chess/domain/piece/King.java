package chess.domain.piece;

import chess.domain.path.Path;
import chess.domain.path.PathFactory;

public class King extends Piece {
    private static final double SCORE = 0;

    private King(PieceColor color, Path path) {
        super(color, path, PieceType.KING);
    }

    public static King createWhite() {
        return new King(PieceColor.WHITE, PathFactory.KING.create());
    }

    public static King createBlack() {
        return new King(PieceColor.BLACK, PathFactory.KING.create());
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
