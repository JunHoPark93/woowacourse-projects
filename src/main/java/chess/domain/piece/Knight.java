package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.path.PathFactory;

import java.util.Set;

public class Knight extends Piece {
    private static final double SCORE = 2.5;

    private Knight(PieceColor color) {
        super(color, PieceType.KNIGHT);
    }

    public static Knight createWhite() {
        return new Knight(PieceColor.WHITE);
    }

    public static Knight createBlack() {
        return new Knight(PieceColor.BLACK);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public Set<Vector> movableList(Square source) {
        return PathFactory.KNIGHT.create().movableList(source);
    }
}
