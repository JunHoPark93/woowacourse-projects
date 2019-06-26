package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.board.YPosition;
import chess.domain.path.PathFactory;

import java.util.Set;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    private static final double SCORE = 1;

    private Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
    }

    public static Pawn createWhite() {
        return new Pawn(PieceColor.WHITE);
    }

    public static Pawn createBlack() {
        return new Pawn(PieceColor.BLACK);
    }

    @Override
    public Set<Vector> movableList(Square source) {
        Set<Vector> movableList = isBlack() ?
                PathFactory.BLACK_PAWN.create().movableList(source) : PathFactory.WHITE_PAWN.create().movableList(source);

        if (!source.isSameY(new YPosition("2")) && isWhite()) {
            Set<Vector> target = movableList.stream()
                    .filter(vector -> vector.isSameSquare(source.moveUp(2)))
                    .collect(Collectors.toSet());

            movableList.removeAll(target);
            return movableList;
        }

        if (!source.isSameY(new YPosition("7")) && isBlack()) {
            Set<Vector> target = movableList.stream()
                    .filter(vector -> vector.isSameSquare(source.moveDown(2)))
                    .collect(Collectors.toSet());

            movableList.removeAll(target);
            return movableList;
        }

        return movableList;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
