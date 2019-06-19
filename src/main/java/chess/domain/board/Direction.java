package chess.domain.board;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public enum Direction {
    UP(square -> new HashSet<>(square.moveUpToEnd())),
    DOWN(square -> new HashSet<>(square.moveDownToEnd())),
    LEFT(square -> new HashSet<>(square.moveLeftToEnd())),
    RIGHT(square -> new HashSet<>(square.moveRightToEnd())),
    UP_RIGHT(square -> new HashSet<>(square.moveUpRightToEnd())),
    UP_LEFT(square -> new HashSet<>(square.moveUpLeftToEnd())),
    DOWN_RIGHT(square -> new HashSet<>(square.moveDownRightToEnd())),
    DOWN_LEFT(square -> new HashSet<>(square.moveDownLeftToEnd())),
    NONE(square -> new HashSet<>());

    private Function<Square, Set<Square>> function;

    Direction(Function<Square, Set<Square>> function) {
        this.function = function;
    }

    public Set<Square> getList(Square square) {
        return function.apply(square);
    }
}
