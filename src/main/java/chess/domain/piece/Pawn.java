package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.domain.board.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    private boolean isMoved;

    public Pawn(PieceColor color) {
        super(color);
        this.isMoved = false;
    }

    @Override
    public Set<Vector> movableList(Square source) {
        Set<Vector> movableList = new HashSet<>();
        movableList.add(new Vector(source.moveUp(1), Direction.UP));
        movableList.add(new Vector(source.moveUpRight(), Direction.UP_RIGHT));
        movableList.add(new Vector(source.moveUpLeft(), Direction.UP_LEFT));

        if (!isMoved) {
            movableList.add(new Vector(source.moveUp(2), Direction.UP));
            isMoved = true;
        }

        return movableList.stream()
                .filter(vector -> vector.getSquare() != source)
                .collect(Collectors.toSet());
    }

    public boolean isMoved() {
        return isMoved;
    }
}
