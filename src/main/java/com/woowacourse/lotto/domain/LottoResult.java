package com.woowacourse.lotto.domain;

import java.util.Iterator;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> result;

    public LottoResult(Map<Rank, Integer> result) {
        this.result = result;
    }

    public Iterator<Map.Entry<Rank, Integer>> getIterator() {
        return result.entrySet().iterator();
    }

    public double getProfitRatio(PurchaseMoney purchaseMoney) {
        double sum = result.keySet().stream()
                .mapToDouble(rank -> rank.getMoney() * result.get(rank))
                .sum();

        return purchaseMoney.getProfitRatio(sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LottoResult that = (LottoResult) o;

        return result != null ? result.equals(that.result) : that.result == null;
    }

    @Override
    public int hashCode() {
        return result != null ? result.hashCode() : 0;
    }
}
