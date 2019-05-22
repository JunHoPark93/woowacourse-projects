package com.woowacourse.javacoordinate.domain;

import java.util.List;

public abstract class Figure {
    protected Points points;

    public Figure(Points points) {
        this.points = points;
    }

    public abstract double calculateArea();

    public double calculateLength() {
        Point point = points.getPoints().get(0);
        List<Point> vertices = points.getPoints();
        double distance = 0;

        for (int i = 1; i < points.getSize(); i++) {
            distance += point.getDistance(vertices.get(i));
        }

        return distance;
    }
}
