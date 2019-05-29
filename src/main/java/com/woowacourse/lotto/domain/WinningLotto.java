package com.woowacourse.lotto.domain;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto lotto, LottoNumber bonusNumber) {
        checkDuplicateBonusNum(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplicateBonusNum(Lotto lotto, LottoNumber bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("중복된 보너스 볼");
        }
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoResult getResult(LottoBuyList lottoBuyList) {
        Map<Rank, Integer> map = initResultMap();
        makeResult(lottoBuyList, map);

        return new LottoResult(map);
    }

    private void makeResult(LottoBuyList lottoBuyList, Map<Rank, Integer> map) {
        for (Lotto lotto: lottoBuyList.getLottoBuyList()) {
            int matchCount = this.lotto.matchCount(lotto);
            boolean isBonusMatch = lotto.isBonusMatch(bonusNumber);
            Rank rank = Rank.getRank(matchCount, isBonusMatch);
            map.put(rank, map.get(rank) + 1);
        }
    }

    private Map<Rank, Integer> initResultMap() {
        Map<Rank, Integer> map = new LinkedHashMap<>();
        Arrays.stream(Rank.values())
                .forEach(rank -> map.put(rank, 0));
        return map;
    }
}
