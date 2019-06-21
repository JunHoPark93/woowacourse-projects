package chess.domain.path;

import chess.domain.board.Direction;
import chess.domain.board.Square;
import chess.domain.board.Vector;

import java.util.HashSet;
import java.util.Set;

public class QueenPath implements Path {
    @Override
    public Set<Vector> movableList(Square source) {
        Set<Vector> movableList = new HashSet<>();
        for (Square square : source.moveUpToEnd()) {
            movableList.add(new Vector(square, Direction.UP));
        }
        for (Square square : source.moveDownToEnd()) {
            movableList.add(new Vector(square, Direction.DOWN));
        }
        for (Square square : source.moveLeftToEnd()) {
            movableList.add(new Vector(square, Direction.LEFT));
        }
        for (Square square : source.moveRightToEnd()) {
            movableList.add(new Vector(square, Direction.RIGHT));
        }

        for (Square square : source.moveUpRightToEnd()) {
            movableList.add(new Vector(square, Direction.UP_RIGHT));
        }
        for (Square square : source.moveUpLeftToEnd()) {
            movableList.add(new Vector(square, Direction.UP_LEFT));
        }
        for (Square square : source.moveDownRightToEnd()) {
            movableList.add(new Vector(square, Direction.DOWN_RIGHT));
        }
        for (Square square : source.moveDownLeftToEnd()) {
            movableList.add(new Vector(square, Direction.DOWN_LEFT));
        }
        return movableList;
    }
}
