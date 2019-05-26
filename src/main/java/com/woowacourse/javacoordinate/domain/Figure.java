package com.woowacourse.javacoordinate.domain;

public abstract class Figure implements FigureCalculable {
    protected Points points;

    public Figure(Points points) {
        this.points = points;
    }

    public Points getPoints() {
        return points;
    }
}
