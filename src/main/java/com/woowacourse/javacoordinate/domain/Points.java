package com.woowacourse.javacoordinate.domain;

import java.util.*;

public class Points {
    private static final int RECTANGULAR_POINT_NUMBER = 4;

    private final List<Point> points;

    public Points(List<Point> points) {
        checkPointsIsDuplicate(points);
        if (points.size() == RECTANGULAR_POINT_NUMBER) {
            checkValidRectangle(points);
        }
        this.points = points;
    }

    private void checkPointsIsDuplicate(List<Point> points) {
        Set<Point> pointSet = new HashSet<>(points);
        if (pointSet.size() != points.size()) {
            throw new IllegalArgumentException("중복된 좌표는 입력할 수 없습니다");
        }
    }

    private void checkValidRectangle(List<Point> points) {
        Point point1 = points.get(0);
        Point point2 = points.get(1);
        Point point3 = points.get(2);
        Point point4 = points.get(3);

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

    public List<Point> getPoints() {
        return points;
    }

    public int getSize() {
        return points.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Points points1 = (Points) o;

        return points != null ? points.equals(points1.points) : points1.points == null;
    }

    @Override
    public int hashCode() {
        return points != null ? points.hashCode() : 0;
    }
}
