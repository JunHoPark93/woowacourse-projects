package com.woowacourse.javacoordinate.domain;

public class Line extends Figure {
    public Line(Points points) {
        super(points);

        if (points.getSize() != 2) {
            throw new IllegalArgumentException("라인은 2개의 Point가 필요합니다");
        }
    }

    @Override
    public double calculateArea() {
        throw new RuntimeException("선은 넓이가 존재하지 않습니다.");
    }
}
