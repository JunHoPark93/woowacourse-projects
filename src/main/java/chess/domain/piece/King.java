package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.path.PathFactory;

import java.util.Set;

public class King extends Piece {
    private static final double SCORE = 0;

    private King(PieceColor color) {
        super(color, PieceType.KING);
    }

    public static King createWhite() {
        return new King(PieceColor.WHITE);
    }

    public static King createBlack() {
        return new King(PieceColor.BLACK);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public Set<Vector> movableList(Square source) {
        return PathFactory.KING.create().movableList(source);
    }
}
