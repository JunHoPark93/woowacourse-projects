package com.woowacourse.javacoordinate.domain;

public abstract class Figure implements FigureCalculator{
    protected Points points;

    public Figure(Points points) {
        this.points = points;
    }
}
