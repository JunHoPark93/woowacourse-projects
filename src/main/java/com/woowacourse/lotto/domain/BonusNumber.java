package com.woowacourse.lotto.domain;

public class BonusNumber implements Number {
    private final int bonusNum;

    public BonusNumber(String input, Lotto lastWeekLotto) {
        int bonusNum = Integer.parseInt(input);
        checkDuplicateBonusNum(lastWeekLotto, bonusNum);
        this.bonusNum = bonusNum;
    }

    private void checkDuplicateBonusNum(Lotto lastWeekLotto, int bonusNum) {
        if (lastWeekLotto.contains(new LottoNumber(bonusNum))) {
            throw new IllegalArgumentException("보너스 번호와 로또번호가 중복입니다");
        }
    }

    @Override
    public int value() {
        return bonusNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BonusNumber that = (BonusNumber) o;

        return bonusNum == that.bonusNum;
    }

    @Override
    public int hashCode() {
        return bonusNum;
    }

    @Override
    public String toString() {
        return String.valueOf(bonusNum);
    }
}
