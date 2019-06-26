package chess.domain.piece;

import chess.domain.path.Path;
import chess.domain.path.PathFactory;

public class Knight extends Piece {
    private static final double SCORE = 2.5;

    private Knight(PieceColor color, Path path) {
        super(color, path, PieceType.KNIGHT);
    }

    public static Knight createWhite() {
        return new Knight(PieceColor.WHITE, PathFactory.KNIGHT.create());
    }

    public static Knight createBlack() {
        return new Knight(PieceColor.BLACK, PathFactory.KNIGHT.create());
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
