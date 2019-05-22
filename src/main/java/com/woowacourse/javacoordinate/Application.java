package com.woowacourse.javacoordinate;

import com.woowacourse.javacoordinate.domain.Calculator;
import com.woowacourse.javacoordinate.domain.Point;
import com.woowacourse.javacoordinate.domain.Points;
import com.woowacourse.javacoordinate.view.InputView;

public class Application {

    public static void main(String[] args) {
        Points points = InputView.inputCoordinatePoints();
        calculate(points);
        System.out.println();
    }

    private static double calculate(Points points) {
        int size = points.getSize();

        if (size == 2) {
            Point point1 = points.getPoints().get(0);
            Point point2 = points.getPoints().get(1);
            return Calculator.calculateLineDistance(point1, point2);
        }

        // TODO
        if (size == 3) {

        }

        if (size == 4) {

        }
        return 0;
    }

}
