package com.woowacourse.lotto.domain;

public enum Rank {
    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NONE(0, 0);

    private int rank;
    private int money;

    Rank(int rank, int money) {
        this.rank = rank;
        this.money = money;
    }

    public static Rank getRank(int matchCount, boolean bonusMatch) {
        if (matchCount == 6) {
            return FIRST;
        }

        if (matchCount == 5 && bonusMatch) {
            return SECOND;
        }

        if (matchCount == 5) {
            return THIRD;
        }

        if (matchCount == 4) {
            return FOURTH;
        }

        if (matchCount == 3) {
            return FIFTH;
        }

        return NONE;
    }

    public void printRank() {
        if (money == 30000000) {
            System.out.print(rank + "개 일치, 보너스 볼 일" + "(" + money + "원)" + "-");
            return;
        }
        System.out.print(rank + "개 일치" + "(" + money + "원)" + "-");
    }

    public int getRank() {
        return rank;
    }

    public int getMoney() {
        return money;
    }
}
