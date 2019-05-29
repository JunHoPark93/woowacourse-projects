package com.woowacourse.lotto.domain;

public class ManualNumber {
    private static final int LOTTO_PRICE = 1000;

    private final int num;

    public ManualNumber(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public int getTotalPrice() {
        return num * LOTTO_PRICE;
    }
}
