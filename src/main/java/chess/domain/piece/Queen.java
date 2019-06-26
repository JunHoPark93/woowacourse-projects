package chess.domain.piece;

import chess.domain.path.Path;
import chess.domain.path.PathFactory;

public class Queen extends Piece {
    private static final double SCORE = 9;

    private Queen(PieceColor color, Path path) {
        super(color, path, PieceType.QUEEN);
    }

    public static Queen createWhite() {
        return new Queen(PieceColor.WHITE, PathFactory.QUEEN.create());
    }

    public static Queen createBlack() {
        return new Queen(PieceColor.BLACK, PathFactory.QUEEN.create());
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
