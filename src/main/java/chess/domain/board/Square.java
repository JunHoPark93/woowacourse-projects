package chess.domain.board;

import java.util.ArrayList;
import java.util.List;

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

    public List<Square> moveUpToEnd() {
        List<Square> movableList = new ArrayList<>();
        List<YPosition> yPositions = y.moveUpToEnd();

        for (YPosition yPosition : yPositions) {
            movableList.add(new Square(x, yPosition));
        }

        return movableList;
    }

    public List<Square> moveDownToEnd() {
        List<Square> movableList = new ArrayList<>();
        List<YPosition> yPositions = y.moveDownToEnd();

        for (YPosition yPosition : yPositions) {
            movableList.add(new Square(x, yPosition));
        }

        return movableList;
    }

    public List<Square> moveRightToEnd() {
        List<Square> movableList = new ArrayList<>();
        List<XPosition> xPositions = x.moveRightToEnd();

        for (XPosition xPosition : xPositions) {
            movableList.add(new Square(xPosition, y));
        }
        return movableList;
    }

    public List<Square> moveLeftToEnd() {
        List<Square> movableList = new ArrayList<>();
        List<XPosition> xPositions = x.moveLeftToEnd();

        for (XPosition xPosition : xPositions) {
            movableList.add(new Square(xPosition, y));
        }
        return movableList;
    }

    public List<Square> moveUpRightToEnd() {
        List<Square> movableList = new ArrayList<>();
        Square moved = moveUpRight();
        Square preSquare = this;

        while (moved != preSquare) {
            movableList.add(moved);
            preSquare = moved;
            moved = moved.moveUpRight();
        }

        return movableList;
    }

    public List<Square> moveUpLeftToEnd() {
        List<Square> movableList = new ArrayList<>();
        Square moved = moveUpLeft();
        Square preSquare = this;

        while (moved != preSquare) {
            movableList.add(moved);
            preSquare = moved;
            moved = moved.moveUpLeft();
        }

        return movableList;
    }

    public List<Square> moveDownRightToEnd() {
        List<Square> movableList = new ArrayList<>();
        Square moved = moveDownRight();
        Square preSquare = this;

        while (moved != preSquare) {
            movableList.add(moved);
            preSquare = moved;
            moved = moved.moveDownRight();
        }

        return movableList;
    }

    public List<Square> moveDownLeftToEnd() {
        List<Square> movableList = new ArrayList<>();
        Square moved = moveDownLeft();
        Square preSquare = this;

        while (moved != preSquare) {
            movableList.add(moved);
            preSquare = moved;
            moved = moved.moveDownLeft();
        }

        return movableList;
    }

    public Square moveUpLeft() {
        Square moved = new Square(x.moveLeft(1), y.moveUp(1));
        if (isLocatedSameLine(moved)) {
            return this;
        }
        return moved;
    }

    public Square moveUpRight() {
        Square moved = new Square(x.moveRight(1), y.moveUp(1));
        if (isLocatedSameLine(moved)) {
            return this;
        }
        return moved;
    }

    public Square moveDownLeft() {
        Square moved = new Square(x.moveLeft(1), y.moveDown(1));
        if (isLocatedSameLine(moved)) {
            return this;
        }
        return moved;
    }

    public Square moveDownRight() {
        Square moved = new Square(x.moveRight(1), y.moveDown(1));
        if (isLocatedSameLine(moved)) {
            return this;
        }
        return moved;
    }

    public boolean isLocatedSameLine(Square moved) {
        return moved.isSameX(this.x) || moved.isSameY(this.y);
    }

    public boolean isSameX(XPosition x) {
        return this.x.equals(x);
    }

    public boolean isSameY(YPosition y) {
        return this.y.equals(y);
    }

    public boolean isVertical(Square square) {
        return this.x.equals(square.x);
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

    @Override
    public String toString() {
        return "" + x + y;
    }
}
