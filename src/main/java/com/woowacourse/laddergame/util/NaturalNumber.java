package com.woowacourse.laddergame.util;

public class NaturalNumber {
    private int number;

    public NaturalNumber(int number) {
        if (number < 0) {
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
