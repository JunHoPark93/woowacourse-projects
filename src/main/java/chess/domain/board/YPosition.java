package chess.domain.board;

public class YPosition {
    private final String yPosition;

    public YPosition(String yPosition) {
        checkYPosition(yPosition);
        this.yPosition = yPosition;
    }

    private void checkYPosition(String yPosition) {
        if (1 > Integer.valueOf(yPosition) || Integer.valueOf(yPosition) > 8) {
            throw new IllegalArgumentException("범위를 벗어났습니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YPosition yPosition1 = (YPosition) o;

        return yPosition != null ? yPosition.equals(yPosition1.yPosition) : yPosition1.yPosition == null;
    }

    @Override
    public int hashCode() {
        return yPosition != null ? yPosition.hashCode() : 0;
    }
}
