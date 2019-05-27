package com.woowacourse.javacoordinate.view;

import com.woowacourse.javacoordinate.domain.Figure;
import com.woowacourse.javacoordinate.util.FigureFactory;
import com.woowacourse.javacoordinate.domain.Point;
import com.woowacourse.javacoordinate.domain.Points;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NATURAL_NUMBER = "[0-9][0-9]*";
    private static final String POINT = "\\((" + NATURAL_NUMBER + "," + NATURAL_NUMBER + ")\\)";
    private static final Pattern POINTS_PATTERN = Pattern.compile(POINT + "(?:-" + POINT + "){1,3}");
    private static final String END_INPUT = "-1";

    public static Figure inputCoordinatePoints() {
        System.out.println("좌표를 입력해주세요.");
        try {
            String input = SCANNER.nextLine();
            checkInput(input);

            return parseInput(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCoordinatePoints();
        }
    }

    private static Figure parseInput(String input) {
        List<Point> points = new ArrayList<>();
        String[] tokens = input.split("-");
        SpitPoints(points, tokens);

        return FigureFactory.getFigure(new Points(points));
    }

    private static void SpitPoints(List<Point> points, String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].replace("(", "");
            tokens[i] = tokens[i].replace(")", "");

            String[] numbers = tokens[i].split(",");
            points.add(new Point(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1])));
        }
    }

    private static void checkInput(String input) {
        checkEndCondition(input);

        Matcher matcher = POINTS_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new IllegalArgumentException("좌표 입력 형식이 맞지 않습니다");
        }
    }

    private static void checkEndCondition(String input) {
        if (input.equals(END_INPUT)) {
            System.exit(0);
        }
    }

    public static int inputCalculateNo() {
        System.out.println("길이는 1번, 넓이는 2번을 입력하세요");
        try {
            String input = SCANNER.nextLine();
            int number = Integer.parseInt(input);
            checkCalculateNo(number);
            return number;
        } catch (NumberFormatException e) {
            System.out.println("유효한 숫자가 아닙니다");
            return inputCalculateNo();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCalculateNo();
        }
    }

    private static void checkCalculateNo(int input) {
        if (input != 1 && input != 2) {
            throw new IllegalArgumentException("1또는 2를 입력하세요");
        }
    }
}
