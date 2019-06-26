package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.path.PathFactory;

import java.util.Set;

public class Bishop extends Piece {
    private static final double SCORE = 3;

    private Bishop(PieceColor color) {
        super(color, PieceType.BISHOP);
    }

    public static Bishop createWhite() {
        return new Bishop(PieceColor.WHITE);
    }

    public static Bishop createBlack() {
        return new Bishop(PieceColor.BLACK);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public Set<Vector> movableList(Square source) {
        return PathFactory.BISHOP.create().movableList(source);
    }
}
