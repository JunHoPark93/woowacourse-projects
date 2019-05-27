package com.woowacourse.javacoordinate.domain;

import java.util.List;

public class CoordinateLine {
    private static final String COORDINATE_SPOT = "*";
    private static final String COORDINATE_BLANK = "  ";

    private final List<Boolean> line;

    public CoordinateLine(List<Boolean> line) {
        this.line = line;
    }

    public boolean isMarked(int index) {
        return line.get(index);
    }

    public String printLine() {
        StringBuilder sb = new StringBuilder();

        for (Boolean point : line) {
            if (point) {
                sb.append(COORDINATE_SPOT);
                continue;
            }
            sb.append(COORDINATE_BLANK);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoordinateLine that = (CoordinateLine) o;

        return line != null ? line.equals(that.line) : that.line == null;
    }

    @Override
    public int hashCode() {
        return line != null ? line.hashCode() : 0;
    }
}
