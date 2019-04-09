/*
 * Class:   Rank
 *
 * Version: 1.0.0
 *
 * Date:    2019-04-09
 *
 * Author:  WooWaBros
 *
 */

package com.junhopark.javalotto.domain;

/**
 * 로또 등수를 의미하는 enum
 */
public enum Rank {
    FIRST(6, 2_000_000_000), // 1등
    SECOND(5, 30_000_000), // 2등
    THIRD(5, 1_500_000), // 3등
    FOURTH(4, 50_000), // 4등
    FIFTH(3, 5_000), // 5등
    MISS(0, 0);

    private static final int WINNING_MIN_COUNT = 3;

    private int countOfMatch;
    private int winningMoney;

    private Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }

    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        if (countOfMatch < WINNING_MIN_COUNT) {
            return MISS;
        }

        if (SECOND.matchCount(countOfMatch) && matchBonus) {
            return SECOND;
        }

        if (THIRD.matchCount(countOfMatch) && !matchBonus) {
            return THIRD;
        }

        for (Rank rank : values()) {
            if (rank.matchCount(countOfMatch)) {
                return rank;
            }
        }

        throw new IllegalArgumentException(countOfMatch + "는 유효하지 않은 값입니다.");
    }

    public void printResult(Long totalCount) {
        System.out.println(countOfMatch + "개 일치 " + "(" + winningMoney + "원)-" + totalCount + "개");
    }

    private boolean matchCount(int countOfMatch) {
        return this.countOfMatch == countOfMatch;
    }
}

