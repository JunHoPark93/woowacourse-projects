package chess.domain.path;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.domain.board.Vector;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class KingPath implements Path {
    @Override
    public Set<Vector> movableList(Square source) {
        Set<Vector> movableList = new HashSet<>();

        movableList.add(new Vector(source.moveUp(1), Direction.UP));
        movableList.add(new Vector(source.moveRight(1), Direction.RIGHT));
        movableList.add(new Vector(source.moveDown(1), Direction.DOWN));
        movableList.add(new Vector(source.moveLeft(1), Direction.LEFT));
        movableList.add(new Vector(source.moveUpRight(), Direction.UP_RIGHT));
        movableList.add(new Vector(source.moveDownRight(), Direction.DOWN_RIGHT));
        movableList.add(new Vector(source.moveDownLeft(), Direction.DOWN_LEFT));
        movableList.add(new Vector(source.moveUpLeft(), Direction.UP_LEFT));

        return movableList.stream()
                .filter(vector -> !vector.isSameSquare(source))
                .collect(Collectors.toSet());
    }
}