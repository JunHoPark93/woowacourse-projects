package com.woowacourse.lotto.domain;

public class PurchaseMoney {
    private static final int MIN_PURCHASE_MONEY = 1000;

    private final int money;

    public PurchaseMoney(int money) {
        checkMoney(money);
        this.money = money;
    }

    private void checkMoney(int money) {
        if (money < MIN_PURCHASE_MONEY) {
            throw new IllegalArgumentException("구입 금액이 부족합니다");
        }
    }
}
