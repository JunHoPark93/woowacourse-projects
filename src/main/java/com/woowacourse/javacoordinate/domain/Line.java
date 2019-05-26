package com.woowacourse.javacoordinate.domain;

import java.util.List;

public class Line extends Figure {
    private static final String SHAPE_NAME = "선분";
    private static final Double LINE_AREA = 0.0;
    private static final int TRIANGLE_POINT_NUMBER = 2;

    public Line(Points points) {
        super(points);

        if (points.getSize() != TRIANGLE_POINT_NUMBER) {
            throw new IllegalArgumentException("라인은 2개의 Point가 필요합니다");
        }
    }

    @Override
    public double calculateArea() {
        return LINE_AREA;
    }

    @Override
    public double calculateLength() {
        Point point = points.getPoints().get(0);
        List<Point> vertices = points.getPoints();
        double distance = 0;

        for (int i = 1; i < points.getSize(); i++) {
            distance += point.getDistance(vertices.get(i));
        }

        return distance;
    }

    @Override
    public String getName() {
        return SHAPE_NAME;
    }
}
