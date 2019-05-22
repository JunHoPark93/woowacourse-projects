package com.woowacourse.javacoordinate.domain;

public abstract class Figure {
    protected Points points;

    public Figure(Points points) {
        this.points = points;
    }

    public abstract double calculateArea();

    public abstract double calculateLength();
}
