package chess.domain.path;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.domain.board.Vector;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WhitePawnPath implements Path {
    @Override
    public Set<Vector> movableList(Square source) {
        Set<Vector> movableList = new LinkedHashSet<>();
        movableList.add(new Vector(source.moveUp(1), Direction.UP));
        movableList.add(new Vector(source.moveUpRight(), Direction.UP_RIGHT));
        movableList.add(new Vector(source.moveUpLeft(), Direction.UP_LEFT));
        movableList.add(new Vector(source.moveUp(2), Direction.UP));

        return movableList.stream()
                .filter(vector -> !(vector.isSameSquare(source)))
                .collect(Collectors.toSet());
    }
}
