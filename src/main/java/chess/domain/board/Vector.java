package chess.domain.board;

import java.util.Set;
import java.util.stream.Collectors;

public class Vector {
    private Square square;
    private Direction direction;

    public Vector(Square square, Direction direction) {
        this.square = square;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public Square getSquare() {
        return square;
    }

    public Set<Vector> vectorList() {
        Set<Square> squares = direction.getList(this.square);
        return squares.stream()
                .map(square -> new Vector(square, direction))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector vector = (Vector) o;

        if (square != null ? !square.equals(vector.square) : vector.square != null) return false;
        return direction == vector.direction;
    }

    @Override
    public int hashCode() {
        int result = square != null ? square.hashCode() : 0;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return square + " " + direction;
    }
}
