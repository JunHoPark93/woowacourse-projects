package coordinate;

import java.util.List;

public class Triangle extends AbstractFigure {
    public Triangle(List<Point> points) {
        super(points);
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public String getName() {
        return "삼각형";
    }

    @Override
    public double area() {
        return 0;
    }
}
