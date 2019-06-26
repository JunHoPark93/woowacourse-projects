package chess.domain.board;

import java.util.List;

public class XPosition {
    private final String xPosition;
    private final Character START_TYPE = 'a';
    private final Character END_TYPE = 'h';

    public XPosition(String xPosition) {
        checkXPosition(xPosition);
        this.xPosition = xPosition;
    }

    private void checkXPosition(String xPosition) {
        if (START_TYPE > xPosition.charAt(0) || xPosition.charAt(0) > END_TYPE) {
            throw new IllegalArgumentException("범위를 벗어났습니다");
        }
    }

    public XPosition moveLeft(int moveCnt) {
        return XPositionFactory.left(xPosition, moveCnt);
    }

    public XPosition moveRight(int moveCnt) {
        return XPositionFactory.right(xPosition, moveCnt);
    }

    public List<XPosition> moveRightToEnd() {
        return XPositionFactory.rightToEnd(xPosition);
    }

    public List<XPosition> moveLeftToEnd() {
        return XPositionFactory.leftToEnd(xPosition);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XPosition xPosition1 = (XPosition) o;

        return xPosition != null ? xPosition.equals(xPosition1.xPosition) : xPosition1.xPosition == null;
    }

    @Override
    public int hashCode() {
        return xPosition != null ? xPosition.hashCode() : 0;
    }

    @Override
    public String toString() {
        return xPosition;
    }
}
