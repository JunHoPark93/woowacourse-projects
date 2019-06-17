package chess.domain.board;


public class Square {
    private final XPosition x;
    private final YPosition y;

    public Square(XPosition x, YPosition y) {
        this.x = x;
        this.y = y;
    }

    public Square moveLeft(int moveCnt) {
        return new Square(x.moveLeft(moveCnt), y);
    }

    public Square moveRight(int moveCnt) {
        return new Square(x.moveRight(moveCnt), y);
    }

    public Square moveUp(int moveCnt) {
        return new Square(x, y.moveUp(moveCnt));
    }

    public Square moveDown(int moveCnt) {
        return new Square(x, y.moveDown(moveCnt));
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
