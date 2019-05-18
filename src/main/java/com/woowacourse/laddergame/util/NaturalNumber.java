package com.woowacourse.laddergame.util;

public class NaturalNumber {
    private static final int NATURAL_NUM_BOUNDARY = 0;
    private int number;

    public NaturalNumber(int number) {
        if (number <= NATURAL_NUM_BOUNDARY) {
            throw new IllegalArgumentException("자연수가 아닙니다.");
        }

        this.number = number;
    }

    public int convertIndex() {
        return number - 1;
    }

    public int getNumber() {
        return number;
    }
}
