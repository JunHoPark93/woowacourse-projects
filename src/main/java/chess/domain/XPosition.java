package chess.domain;

public class XPosition {
    private final String xPosition;

    public XPosition(String xPosition) {
        checkXPosition(xPosition);
        this.xPosition = xPosition;
    }

    private void checkXPosition(String xPosition) {
        if ('a' > xPosition.charAt(0) || xPosition.charAt(0) > 'h') {
            throw new IllegalArgumentException("범위를 벗어났습니다");
        }
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
}
