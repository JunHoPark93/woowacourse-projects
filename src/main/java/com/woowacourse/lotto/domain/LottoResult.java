package com.woowacourse.lotto.domain;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> result = new LinkedHashMap<>();

    public LottoResult(LottoBuyList totalBuys, WinningLotto winningLotto) {
        initResultMap();
        makeResult(totalBuys, winningLotto);
    }

    private void initResultMap() {
        Arrays.stream(Rank.values())
                .forEach(rank -> result.put(rank, 0));
    }

    private void makeResult(LottoBuyList lottoBuyList, WinningLotto winningLotto) {
        for (Lotto lotto: lottoBuyList.getLottoBuyList()) {
            int matchCount = winningLotto.matchCount(lotto);
            boolean isBonusMatch = winningLotto.isBonusMatch(lotto);
            Rank rank = Rank.getRank(matchCount, isBonusMatch);
            result.put(rank, result.get(rank) + 1);
        }
    }

    public Iterator<Map.Entry<Rank, Integer>> getIterator() {
        return result.entrySet().iterator();
    }

    public double calculateProfitRatio(PurchaseMoney purchaseMoney) {
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

        return result.equals(that.result);
    }

    @Override
    public int hashCode() {
        return result.hashCode();
    }
}
