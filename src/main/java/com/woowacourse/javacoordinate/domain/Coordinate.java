package com.woowacourse.javacoordinate.domain;

public class Coordinate {
    private static final int MIN_BOUNDARY = 0;
    private static final int MAX_BOUNDARY = 24;

    private final int coordinate;

    public Coordinate(int coordinate) {
        checkPointRange(coordinate);
        this.coordinate = coordinate;
    }

    private void checkPointRange(int coordinate) {
        if (coordinate < MIN_BOUNDARY || coordinate > MAX_BOUNDARY) {
            throw new IllegalArgumentException("좌표 입력 범위를 넘어갔습니다");
        }
    }

    public int getCoordinate() {
        return coordinate;
    }
}
