package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.domain.board.Vector;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Knight extends Piece {
    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public Set<Vector> movableList(Square source) {
        Set<Vector> movableList = new HashSet<>();
        movableList.add(new Vector(source.moveUp(1).moveUpRight(), Direction.NONE));
        movableList.add(new Vector(source.moveRight(1).moveUpRight(), Direction.NONE));
        movableList.add(new Vector(source.moveRight(1).moveDownRight(), Direction.NONE));
        movableList.add(new Vector(source.moveDown(1).moveDownRight(), Direction.NONE));
        movableList.add(new Vector(source.moveDown(1).moveDownLeft(), Direction.NONE));
        movableList.add(new Vector(source.moveLeft(1).moveDownLeft(), Direction.NONE));
        movableList.add(new Vector(source.moveLeft(1).moveUpLeft(), Direction.NONE));
        movableList.add(new Vector(source.moveUp(1).moveUpLeft(), Direction.NONE));


        movableList.remove(new Vector(source.moveUpRight(), Direction.NONE));
        movableList.remove(new Vector(source.moveUpLeft(), Direction.NONE));
        movableList.remove(new Vector(source.moveDownRight(), Direction.NONE));
        movableList.remove(new Vector(source.moveDownLeft(), Direction.NONE));
        movableList.remove(new Vector(source, Direction.NONE));

        return movableList.stream()
                .filter(vector -> !(source.isLocatedSameLine(vector.getSquare())))
                .collect(Collectors.toSet());
    }
}
