package coordinate;

import java.util.List;

public abstract class AbstractFigure implements Figure {
    private final List<Point> points;

    public AbstractFigure(List<Point> points) {
        if (points.size() != size()) {
            throw new IllegalArgumentException(getName() + "의 길이는 " + size() + "이어야 합니다.");
        }

        this.points = points;
    }

    protected Point getPoint(int index) {
        return points.get(index);
    }

    @Override
    public List<Point> getPoints() {
        return points;
    }
}
