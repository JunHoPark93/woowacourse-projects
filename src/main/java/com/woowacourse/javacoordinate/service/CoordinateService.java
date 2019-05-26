package com.woowacourse.javacoordinate.service;

import com.woowacourse.javacoordinate.domain.CoordinateLine;
import com.woowacourse.javacoordinate.domain.CoordinateSystem;
import com.woowacourse.javacoordinate.domain.Point;
import com.woowacourse.javacoordinate.domain.Points;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoordinateService {

    public static CoordinateSystem makeCoordinateSystem(Points points) {
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
}
