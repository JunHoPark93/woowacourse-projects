package com.woowacourse.javacoordinate.domain;

public class Calculator {
    public static double calculateLineDistance(Point point1, Point point2) {
        return point1.getDistance(point2);
    }
}
