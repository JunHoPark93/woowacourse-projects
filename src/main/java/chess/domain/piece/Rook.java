package chess.domain.piece;

import chess.domain.path.Path;
import chess.domain.path.PathFactory;

public class Rook extends Piece {
    private static final double SCORE = 5;

    private Rook(PieceColor color, Path path) {
        super(color, path, PieceType.ROOK);
    }

    public static Rook createBlack() {
        return new Rook(PieceColor.BLACK, PathFactory.ROOK.create());
    }

    public static Rook createWhite() {
        return new Rook(PieceColor.WHITE, PathFactory.ROOK.create());
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
