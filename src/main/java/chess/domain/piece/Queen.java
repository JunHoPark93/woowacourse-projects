package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.path.PathFactory;

import java.util.Set;

public class Queen extends Piece {
    private static final double SCORE = 9;

    private Queen(PieceColor color) {
        super(color, PieceType.QUEEN);
    }

    public static Queen createWhite() {
        return new Queen(PieceColor.WHITE);
    }

    public static Queen createBlack() {
        return new Queen(PieceColor.BLACK);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public Set<Vector> movableList(Square source) {
        return PathFactory.QUEEN.create().movableList(source);
    }
}
