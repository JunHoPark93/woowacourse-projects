package com.woowacourse.javacoordinate.view;

import com.woowacourse.javacoordinate.domain.CoordinateLine;
import com.woowacourse.javacoordinate.domain.CoordinateSystem;
import com.woowacourse.javacoordinate.domain.Result;

import java.util.List;

public class OutputView {
    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 24;

    public static void printCoordinateSystem(CoordinateSystem coordinateSystem) {
        List<CoordinateLine> lines = coordinateSystem.getCoordinateLines();

        for (int i = MAX_COORDINATE; i >= MIN_COORDINATE; i--) {
            printCoordinateLine(lines, i);
        }

        // 마지막 줄
        printXAxis();
        System.out.println();
    }

    private static void printCoordinateLine(List<CoordinateLine> lines, int i) {
        if (checkYAxisMarkingLine(lines, i)) {
            return;
        }

        if (checkYAxisZeroLIne(i)) {
            return;
        }
        System.out.println("  " + "|" + lines.get(i));
    }

    private static boolean checkYAxisMarkingLine(List<CoordinateLine> lines, int i) {
        if (i != 0 && i % 2 == 0) {
            System.out.println(String.format("%2d|", i) + lines.get(i));

            return true;
        }
        return false;
    }

    private static boolean checkYAxisZeroLIne(int i) {
        if (i == 0) {
            System.out.print(" 0+");
            printDash();
            System.out.println();
            return true;
        }
        return false;
    }

    private static void printDash() {
        for (int j = 0; j < MAX_COORDINATE; j++) {
            System.out.print("--");
        }
    }

    private static void printXAxis() {
        System.out.print("   0");
        for (int i = 1; i <= MAX_COORDINATE; i++) {
            checkEvenXAxis(i);
        }
    }

    private static void checkEvenXAxis(int i) {
        if (i % 2 == 0) {
            System.out.print(String.format("%4d", i));
        }
    }

    public static void printResult(Result result) {
        checkResultIfLine(result);
        checkResultIfTriangle(result);
        checkResultIfRectangle(result);
    }

    private static void checkResultIfLine(Result result) {
        if (result.getResultType().equals("Line")) {
            System.out.println("두 점 사이의 거리는 " + result.getResult());
        }
    }

    private static void checkResultIfTriangle(Result result) {
        if (result.getResultType().equals("Triangle")) {
            System.out.println("삼각형 넓이는" + String.format("%.1f", result.getResult()));
        }
    }

    private static void checkResultIfRectangle(Result result) {
        if (result.getResultType().equals("Rectangle")) {
            System.out.println("사각형 넓이는" + String.format("%.0f", result.getResult()));
        }
    }
}
