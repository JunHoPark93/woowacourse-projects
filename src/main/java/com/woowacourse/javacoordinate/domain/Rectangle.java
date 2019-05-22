package com.woowacourse.javacoordinate.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Rectangle extends Figure {
    private static final int RECTANGLE_POINT_NUMBER = 4;

    public Rectangle(Points points) {
        super(points);

        if (points.getSize() != RECTANGLE_POINT_NUMBER) {
            throw new IllegalArgumentException("사각형은 4개의 Point가 필요합니다");
        }
        checkValidRectangle(points);
    }

    private void checkValidRectangle(Points points) {
        List<Point> vertices = points.getPoints();
        Point point1 = vertices.get(0);
        Point point2 = vertices.get(1);
        Point point3 = vertices.get(2);
        Point point4 = vertices.get(3);

        double distance1 = point1.getDistance(point2);
        double distance2 = point1.getDistance(point3);
        double distance3 = point1.getDistance(point4);
        double distance4 = point2.getDistance(point3);
        double distance5 = point2.getDistance(point4);

        List<Double> list = Arrays.asList(distance1, distance2, distance3, distance4, distance5);
        Collections.sort(list);

        if (!list.get(4).equals(list.get(3))) {
            throw new IllegalArgumentException("유효한 사각형이 아닙니다");
        }
    }

    @Override
    public double calculateArea() {
        List<Double> list = getDistances();

        return list.get(0) * list.get(1);
    }

    @Override
    public double calculateLength() {
        List<Double> list = getDistances();

        return (list.get(0) + list.get(1)) * 2;
    }

    private List<Double> getDistances() {
        List<Point> vertices = points.getPoints();

        Point point1 = vertices.get(0);
        Point point2 = vertices.get(1);
        Point point3 = vertices.get(2);
        Point point4 = vertices.get(3);

        double distance1 = point1.getDistance(point2);
        double distance2 = point1.getDistance(point3);
        double distance3 = point1.getDistance(point4);

        List<Double> list = Arrays.asList(distance1, distance2, distance3);
        Collections.sort(list);
        return list;
    }
}
