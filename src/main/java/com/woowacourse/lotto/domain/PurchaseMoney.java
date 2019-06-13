package com.woowacourse.lotto.domain;

public class PurchaseMoney {
    private static final int MIN_PURCHASE_MONEY = 1000;

    private final double money;

    public PurchaseMoney(String inputString) {
        double money = Double.parseDouble(inputString);
        checkMoney(money);
        this.money = money;
    }

    private void checkMoney(double money) {
        if (money < MIN_PURCHASE_MONEY) {
            throw new IllegalArgumentException("구입 금액이 부족합니다");
        }
    }

    public int getAvailableLottoSize() {
        return (int) money / MIN_PURCHASE_MONEY;
    }

    public double getProfitRatio(double lottoProfit) {
        return lottoProfit / money * 100;
    }

    public boolean isAcceptableMoney(int money) {
        return this.money >= money;
    }

    public boolean isEnoughMoney(ManualNumber manualNumber) {
        return money - manualNumber.getTotalPrice() >= Lotto.PRICE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseMoney that = (PurchaseMoney) o;

        return Double.compare(that.money, money) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(money);
        return (int) (temp ^ (temp >>> 32));
    }
}
