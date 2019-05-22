package com.woowacourse.javacoordinate.domain;

import java.util.List;

public class Line extends Figure {
    public Line(Points points) {
        super(points);

        if (points.getSize() != 2) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double calculateArea() {
        throw new RuntimeException("선은 넓이가 존재하지 않습니다.");
    }

    public double calculateLength() {
        List<Point> vertices = points.getPoints();

        Point point1 = vertices.get(0);
        Point point2 = vertices.get(1);

        return point1.getDistance(point2);
    }
}
