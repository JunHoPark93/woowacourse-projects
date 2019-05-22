package com.woowacourse.javacoordinate.domain;


public class Point {
    private static final int MIN_BOUNDARY = 0;
    private static final int MAX_BOUNDARY = 24;

    private final int x;
    private final int y;

    public Point(int x, int y) {
        checkValidPointRange(x, y);
        this.x = x;
        this.y = y;
    }

    private void checkValidPointRange(int x, int y) {
        if (x < MIN_BOUNDARY || x > MAX_BOUNDARY || y < MIN_BOUNDARY || y > MAX_BOUNDARY) {
            throw new IllegalArgumentException("좌표 입력 범위를 넘어갔습니다");
        }
    }

    public double getDistance(Point point) {
        int thatX = point.getX();
        int thatY = point.getY();

        return Math.sqrt(Math.pow(thatX - this.x,2) + Math.pow(thatY - this.y , 2));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
