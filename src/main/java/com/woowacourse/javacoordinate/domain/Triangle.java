package com.woowacourse.javacoordinate.domain;

import java.util.List;

public class Triangle extends Figure {
    public Triangle(Points points) {
        super(points);

        if (points.getSize() != 3) {
            throw new IllegalArgumentException("삼각형은 3개의 Point가 필요합니다");
        }
    }

    @Override
    public double calculateArea() {
        List<Point> vertices = points.getPoints();
        Point point1 = vertices.get(0);
        Point point2 = vertices.get(1);
        Point point3 = vertices.get(2);

        double distance1 = point1.getDistance(point2);
        double distance2 = point1.getDistance(point3);
        double distance3 = point2.getDistance(point3);

        double s = (distance1 + distance2 + distance3) / 2;

        return Math.sqrt(s * (s - distance1) * (s - distance2) * (s - distance3));
    }
}
