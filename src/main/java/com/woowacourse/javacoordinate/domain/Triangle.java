package com.woowacourse.javacoordinate.domain;

import java.util.Arrays;
import java.util.List;

public class Triangle extends Figure {
    private static final String SHAPE_NAME = "삼각형";
    private static final int TRIANGLE_POINT_NUMBER = 3;

    public Triangle(Points points) {
        super(points);

        if (points.getSize() != TRIANGLE_POINT_NUMBER) {
            throw new IllegalArgumentException("삼각형은 3개의 Point가 필요합니다");
        }
        checkValidTriangle(points);
    }

    private void checkValidTriangle(Points points) {
        List<Point> vertices = points.getPoints();

        Point point1 = vertices.get(0);
        Point point2 = vertices.get(1);
        Point point3 = vertices.get(2);

        if (getDegrees(point1, point2) == getDegrees(point2, point3)) {
            throw new IllegalArgumentException("삼각형은 세 점이 일직선상에 있으면 안됩니다");
        }
    }

    private double getDegrees(Point point1, Point point2) {
        double xDiff = point2.getX() - point1.getX();
        double yDiff = point2.getY() - point1.getY();

        return Math.toDegrees(Math.atan2(yDiff, xDiff));
    }

    @Override
    public double calculateArea() {
        List<Double> distances = getDistances();

        double s = (distances.get(0) + distances.get(1) + distances.get(2)) / 2;

        return Math.sqrt(s * (s - distances.get(0)) * (s - distances.get(1)) * (s - distances.get(2)));
    }

    @Override
    public double calculateLength() {
        List<Double> distances = getDistances();

        return distances.get(0) + distances.get(1) + distances.get(2);
    }

    private List<Double> getDistances() {
        List<Point> vertices = points.getPoints();
        Point point1 = vertices.get(0);
        Point point2 = vertices.get(1);
        Point point3 = vertices.get(2);

        double distance1 = point1.getDistance(point2);
        double distance2 = point1.getDistance(point3);
        double distance3 = point2.getDistance(point3);

        return Arrays.asList(distance1,distance2,distance3);
    }

    @Override
    public String getName() {
        return SHAPE_NAME;
    }
}