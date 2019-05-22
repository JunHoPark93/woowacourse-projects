package com.woowacourse.javacoordinate.domain;

import java.util.Arrays;
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

}