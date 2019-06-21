package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.*;

public class DrawConsoleService implements DrawService {
    private LottoBuyList totalBuys;
    private Lotto lastWeekLotto;
    private BonusNumber bonusNumber;

    public DrawConsoleService(LottoBuyList totalBuys, Lotto lastWeekLotto, BonusNumber bonusNumber) {
        this.totalBuys = totalBuys;
        this.lastWeekLotto = lastWeekLotto;
        this.bonusNumber = bonusNumber;
    }

    @Override
    public LottoResult createResult() {
        WinningLotto winningLotto = new WinningLotto(lastWeekLotto, bonusNumber);
        return new LottoResult(totalBuys, winningLotto);
    }
}
