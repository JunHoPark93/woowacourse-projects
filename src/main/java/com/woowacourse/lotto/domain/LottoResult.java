package com.woowacourse.lotto.domain;

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
        for (Rank rank : map.keySet()) {
            rank.printRank();
            System.out.println(map.get(rank) + "개");
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
