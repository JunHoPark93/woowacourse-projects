package com.woowacourse.javacoordinate.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Calculator {
    public static double calculateLineDistance(Point point1, Point point2) {
        return point1.getDistance(point2);
    }

    public static double calculateRectangleArea(Point point1, Point point2, Point point3, Point point4) {
        double distance1 = point1.getDistance(point2);
        double distance2 = point1.getDistance(point3);
        double distance3 = point1.getDistance(point4);

        List<Double> list = Arrays.asList(distance1, distance2, distance3);
        Collections.sort(list);

        return list.get(0) * list.get(1);
    }

    public static double calculateTriangleArea(Point point1, Point point2, Point point3) {
        double distance1 = point1.getDistance(point2);
        double distance2 = point1.getDistance(point3);
        double distance3 = point2.getDistance(point3);

        double s = (distance1 + distance2 + distance3) / 2;

        return Math.sqrt(s * (s - distance1) * (s - distance2) * (s - distance3));
    }
}
