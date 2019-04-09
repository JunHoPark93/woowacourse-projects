/*
 * Class:   WinningLotto
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
 * 당첨 번호를 담당하는 객체
 */
public class WinningLotto {
    private final Lotto lotto;
    private final int bonusNo;

    public WinningLotto(Lotto lotto, int bonusNo) {
        this.lotto = lotto;
        this.bonusNo = bonusNo;
    }

    public Rank match(Lotto userLotto) {
        int count = (int) userLotto.getNumbers().stream()
                .filter(i -> lotto.getNumbers().contains(i))
                .count();

        boolean isBonusMatch = userLotto.getNumbers().stream()
                .anyMatch(i -> i == bonusNo);

        return Rank.valueOf(count, isBonusMatch);
    }
}
