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
    public boolean isEmpty() {
        return num == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManualNumber that = (ManualNumber) o;

        return num == that.num;
    }

    @Override
    public int hashCode() {
        return num;
    }
}
