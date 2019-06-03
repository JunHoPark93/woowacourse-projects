package com.woowacourse.lotto.domain;

import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> result;

    public LottoResult(Map<Rank, Integer> result) {
        this.result = result;
    }

    public Map<Rank, Integer> getResult() {
        return result;
    }

    public double getProfitRatio(PurchaseMoney purchaseMoney) {
        double sum = 0.0;
        for (Rank rank : result.keySet()) {
            sum += rank.getMoney() * result.get(rank);
        }

        return purchaseMoney.getProfitRatio(sum);
    }
}
