package com.woowacourse.javacoordinate;

import com.woowacourse.javacoordinate.domain.*;
import com.woowacourse.javacoordinate.view.InputView;
import com.woowacourse.javacoordinate.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    private static final int LINE_VERTEX = 2;
    private static final int TRIANGLE_VERTEX = 3;
    private static final int RECTANGLE_VERTEX = 4;


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
        Boolean[] line = initLine();

        for (int i = 0; i < points.getSize(); i++) {
            Point point = points.getPoints().get(i);
            int x = point.getX();
            int y = point.getY();
            checkMark(yAxis, line, x, y);
        }

        return new CoordinateLine(Arrays.asList(line));
    }

    private static Boolean[] initLine() {
        Boolean[] line = new Boolean[25];
        for (int i = 0; i < line.length; i++) {
            line[i] = false;
        }
        return line;
    }

    private static void checkMark(int yAxis, Boolean[] line, int x, int y) {
        if (yAxis == y) {
            line[x] = true;
        }
    }

    private static Result calculate(Points points) {
        int size = points.getSize();
        if (size == LINE_VERTEX) {
            return calculateLine(points);
        }
        if (size == TRIANGLE_VERTEX) {
            return calculateTriangle(points);
        }
        if (size == RECTANGLE_VERTEX) {
            return calculateRectangle(points);
        }
        throw new IllegalArgumentException("Points 형식이 잘못 되었습니다");
    }

    private static Result calculateLine(Points points) {
        Point point1 = points.getPoints().get(0);
        Point point2 = points.getPoints().get(1);
        double result = Calculator.calculateLineDistance(point1, point2);
        return new Result(result, "Line");
    }

    private static Result calculateTriangle(Points points) {
        Point point1 = points.getPoints().get(0);
        Point point2 = points.getPoints().get(1);
        Point point3 = points.getPoints().get(2);
        double result = Calculator.calculateTriangleArea(point1, point2, point3);
        return new Result(result, "Triangle");
    }

    private static Result calculateRectangle(Points points) {
        Point point1 = points.getPoints().get(0);
        Point point2 = points.getPoints().get(1);
        Point point3 = points.getPoints().get(2);
        Point point4 = points.getPoints().get(3);
        double result = Calculator.calculateRectangleArea(point1, point2, point3, point4);
        return new Result(result, "Rectangle");
    }
}
