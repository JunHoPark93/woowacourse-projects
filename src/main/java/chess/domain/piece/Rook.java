package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.path.PathFactory;

import java.util.Set;

public class Rook extends Piece {
    private static final double SCORE = 5;

    private Rook(PieceColor color) {
        super(color, PieceType.ROOK);
    }

    public static Rook createBlack() {
        return new Rook(PieceColor.BLACK);
    }

    public static Rook createWhite() {
        return new Rook(PieceColor.WHITE);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public Set<Vector> movableList(Square source) {
        return PathFactory.ROOK.create().movableList(source);
    }
}
