package chess.domain;


public class Square {
    private final XPosition x;
    private final YPosition y;

    public Square(XPosition x, YPosition y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Square square = (Square) o;

        if (x != null ? !x.equals(square.x) : square.x != null) return false;
        return y != null ? y.equals(square.y) : square.y == null;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }
}
