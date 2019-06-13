package com.woowacourse.lotto.domain;

public class ManualNumber {
    private final int num;

    public ManualNumber(String input, PurchaseMoney purchaseMoney) {
        int lottoCount = Integer.parseInt(input);
        checkAvailableLottoNum(lottoCount * Lotto.PRICE, purchaseMoney);
        this.num = lottoCount;
    }

    private void checkAvailableLottoNum(int money, PurchaseMoney purchaseMoney) {
        if (!purchaseMoney.isAcceptableMoney(money)) {
            throw new IllegalArgumentException("구매할 돈이 부족합니다");
        }
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
