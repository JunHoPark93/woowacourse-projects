package com.woowacourse.javacoordinate.domain;

import java.util.*;

public class Points {
    private final List<Point> points;

    public Points(List<Point> points) {
        checkPointsIsDuplicate(points);
        this.points = points;
    }

    private void checkPointsIsDuplicate(List<Point> points) {
        Set<Point> pointSet = new HashSet<>(points);
        if (pointSet.size() != points.size()) {
            throw new IllegalArgumentException("중복된 좌표는 입력할 수 없습니다");
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
