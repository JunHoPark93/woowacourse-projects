package com.woowacourse.javacoordinate.domain;

import java.util.List;

public class CoordinateSystem {
    private final List<CoordinateLine> coordinateLines;

    public CoordinateSystem(List<CoordinateLine> coordinateLines) {
        this.coordinateLines = coordinateLines;
    }

    public boolean isMarked(int x, int y) {
        return coordinateLines.get(y).isMarked(x);
    }

    public List<CoordinateLine> getCoordinateLines() {
        return coordinateLines;
    }
}
