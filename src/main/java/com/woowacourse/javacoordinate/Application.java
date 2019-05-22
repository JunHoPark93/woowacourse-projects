package com.woowacourse.javacoordinate;

import com.woowacourse.javacoordinate.domain.*;
import com.woowacourse.javacoordinate.view.InputView;
import com.woowacourse.javacoordinate.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        while (true) {
            Points points = InputView.inputCoordinatePoints();
            CoordinateSystem coordinateSystem = drawCoordinate(points);
            OutputView.printCoordinateSystem(coordinateSystem);
            OutputView.printResult(calculate(points));
        }
    }

    private static CoordinateSystem drawCoordinate(Points points) {

        List<CoordinateLine> coordinateLines = new ArrayList<>();

        for (int yAxis = 0; yAxis < 25; yAxis++) {
            coordinateLines.add(createLine(points, yAxis));
        }

        return new CoordinateSystem(coordinateLines);
    }

    private static CoordinateLine createLine(Points points, int yAxis) {
        Boolean[] line = new Boolean[25];

        for (int i = 0; i < line.length; i++) {
            line[i] = false;
        }

        for (int i = 0; i < points.getSize(); i++) {
            Point point = points.getPoints().get(i);
            int x = point.getX();
            int y = point.getY();

            if (yAxis == y) {
                line[x] = true;
            }
        }

        return new CoordinateLine(Arrays.asList(line));
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
