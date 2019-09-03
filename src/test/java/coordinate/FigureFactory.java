package coordinate;

import java.util.List;

public class FigureFactory {
    static Figure getInstance(List<Point> points) {
        if (points.size() == 2) {
            return new Line(points);
        }

        if (points.size() == 3) {
            return new Triangle(points);
        }

        if (points.size() == 4) {
            return new Rectangle(points);
        }

        throw new IllegalArgumentException("유효하지 않은 도형입니다.");
    }
}
