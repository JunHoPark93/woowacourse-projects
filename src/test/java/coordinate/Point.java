package coordinate;

import java.util.Objects;

public class Point {
    private final int x;
    private final int y;

    private Point(int x, int y) {
        this.x = x;
        if (x < 0 || x > 24) {
            throw new IllegalArgumentException();
        }

        this.y = y;
        if (y < 0 || y > 24) {
            throw new IllegalArgumentException();
        }
    }

    public double getDistance(Point other) {
        int xDifference = other.minusX(x);
        int yDifference = other.minusY(y);
        return Math.sqrt(square(xDifference) + square(yDifference));
    }

    private int minusX(int number) {
        return this.x - number;
    }

    private int minusY(int number) {
        return this.y - number;
    }

    private static int square(int number) {
        return number * number;
    }

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    public static Point ofCommaSeparator(String text) {
        String[] values = text.split(",");
        return new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}
