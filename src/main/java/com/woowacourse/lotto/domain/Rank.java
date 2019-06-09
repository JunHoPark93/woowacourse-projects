package com.woowacourse.lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    NONE(0, 0);

    private int matchCount;
    private int money;

    Rank(int matchCount, int money) {
        this.matchCount = matchCount;
        this.money = money;
    }

    public static Rank getRank(int matchCount, boolean bonusMatch) {
        if (matchCount == SECOND.matchCount && bonusMatch) {
            return SECOND;
        }

        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findAny()
                .orElse(NONE);
    }

    public int getMoney() {
        return money;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
