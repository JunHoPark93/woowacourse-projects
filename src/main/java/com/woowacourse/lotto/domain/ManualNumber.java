package com.woowacourse.lotto.domain;

public class ManualNumber {
    private final int num;

    public ManualNumber(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public int getTotalPrice() {
        return num * Lotto.PRICE;
    }
}
