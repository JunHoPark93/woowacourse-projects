package com.woowacourse.lotto.domain;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NONE(0, 0);

    private int matchCount;
    private int money;

    Rank(int matchCount, int money) {
        this.matchCount = matchCount;
        this.money = money;
    }

    public static Rank getRank(int matchCount, boolean bonusMatch) {
        if (FIRST.match(matchCount)) {
            return FIRST;
        }

        if (SECOND.match(matchCount) && bonusMatch) {
            return SECOND;
        }

        if (THIRD.match(matchCount)) {
            return THIRD;
        }

        if (FOURTH.match(matchCount)) {
            return FOURTH;
        }

        if (FIFTH.match(matchCount)) {
            return FIFTH;
        }

        return NONE;
    }

    private boolean match(int matchCount) {
        return this.matchCount == matchCount;
    }

    public void printRank(int hittingCount) {
        if (money == 0) {
            return;
        }
        if (money == 30000000) {
            System.out.println(matchCount + "개 일치, 보너스 볼 일" + "(" + money + "원)" + "-" + hittingCount + "개");
            return;
        }
        System.out.println(matchCount + "개 일치" + "(" + money + "원)" + "-" + hittingCount + "개");
    }

    public int getMoney() {
        return money;
    }
}
