package com.woowacourse.lotto.domain;

public class WinningLotto {
    private final Lotto lotto;
    private final Number bonusNumber;

    public WinningLotto(Lotto lotto, Number bonusNumber) {
        checkDuplicateBonusNum(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplicateBonusNum(Lotto lotto, Number bonusNumber) {
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("중복된 보너스 볼");
        }
    }

    public Rank match(LottoTicket lotto) {
        int matchCount = this.lotto.matchCount(lotto);
        boolean matchBonus = lotto.contains(bonusNumber);

        return Rank.getRank(matchCount, matchBonus);
    }

    @Override
    public String toString() {
        return "{" +
                "lotto=" + lotto +
                ", bonusNumber=" + bonusNumber +
                '}';
    }
}
