package domain;

public class Point {
    private int point;

    public Point() {
        point = 0;
    }

    public Point(int point) {
        if (point < 0) {
            throw new IllegalArgumentException("포인트는 음수가 될 수 없습니다");
        }
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
