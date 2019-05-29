package com.woowacourse.lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> map;

    public LottoResult(Map<Rank, Integer> map) {
        this.map = map;
    }

    public Map<Rank, Integer> getMap() {
        return map;
    }

    public void printResult() {
        List<Rank> keys = new ArrayList<>(map.keySet());
        for (int i = keys.size() - 1; i >= 0; i--) {
            keys.get(i).printRank(map.get(keys.get(i)));
        }
    }

    public double getProfitRatio(PurchaseMoney purchaseMoney) {
        double sum = 0.0;
        for (Rank rank : map.keySet()) {
            sum += rank.getMoney() * map.get(rank);
        }

        return purchaseMoney.getProfitRatio(sum);
    }
}
