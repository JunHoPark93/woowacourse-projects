package chess.domain.piece;

import chess.domain.board.Square;
import chess.domain.board.Vector;
import chess.domain.board.YPosition;
import chess.domain.path.Path;

import java.util.Set;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    private static final double SCORE = 1;

    public Pawn(PieceColor color, Path path) {
        super(color, path, PieceType.PAWN);
    }

    @Override
    public Set<Vector> movableList(Square source) {
        Set<Vector> movableList = super.movableList(source);

        if (!source.isSameY(new YPosition("2")) && getColor().equals(PieceColor.WHITE)) {
            Set<Vector> target = movableList.stream()
                    .filter(vector -> vector.getSquare().equals(source.moveUp(2)))
                    .collect(Collectors.toSet());

            movableList.removeAll(target);
            return movableList;
        }

        if (!source.isSameY(new YPosition("7")) && getColor().equals(PieceColor.BLACK)) {
            Set<Vector> target = movableList.stream()
                    .filter(vector -> vector.getSquare().equals(source.moveDown(2)))
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
