package chess.domain.path;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.domain.board.Vector;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BlackPawnPath implements Path {
    @Override
    public Set<Vector> movableList(Square source) {
        Set<Vector> movableList = new LinkedHashSet<>();
        movableList.add(new Vector(source.moveDown(1), Direction.DOWN));
        movableList.add(new Vector(source.moveDownRight(), Direction.DOWN_RIGHT));
        movableList.add(new Vector(source.moveDownLeft(), Direction.DOWN_LEFT));
        movableList.add(new Vector(source.moveDown(2), Direction.DOWN));

        return movableList.stream()
                .filter(vector -> !(vector.isSameSquare(source)))
                .collect(Collectors.toSet());
    }
}
