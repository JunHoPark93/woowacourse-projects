package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Vector {
    private final Square square;
    private final Direction direction;

    public Vector(Square square, Direction direction) {
        this.square = square;
        this.direction = direction;
    }

    public Set<Vector> vectorList() {
        Set<Square> squares = direction.getList(this.square);
        return squares.stream()
                .map(square -> new Vector(square, direction))
                .collect(Collectors.toSet());
    }

    public boolean isDownLeft() {
        return this.direction.equals(Direction.DOWN_LEFT);
    }

    public boolean isDownRight() {
        return this.direction.equals(Direction.DOWN_RIGHT);
    }

    public boolean isUpLeft() {
        return this.direction.equals(Direction.UP_LEFT);
    }

    public boolean isUpRight() {
        return this.direction.equals(Direction.UP_RIGHT);
    }

    public boolean isUp() {
        return this.direction.equals(Direction.UP);
    }

    public boolean isDown() {
        return this.direction.equals(Direction.DOWN);
    }

    public boolean isSameSquare(Square source) {
        return this.square.equals(source);
    }

    public boolean containsSameSquare(Set<Vector> vectors) {
        return vectors.stream().anyMatch(vector -> vector.square.equals(this.square));
    }

    public boolean isLocatedSameLine(Square source) {
        return source.isLocatedSameLine(this.square);
    }

    public boolean containsSquare(Map<Square, Piece> pieces) {
        return pieces.containsKey(square);
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
