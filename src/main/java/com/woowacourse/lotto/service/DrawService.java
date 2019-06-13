package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.util.LottoGenerator;
import com.woowacourse.lotto.util.LottoParser;

public class DrawService {
    private LottoBuyList totalBuys;
    protected Lotto lastWeekLotto;
    protected BonusNumber bonusNumber;

    public DrawService(LottoBuyList totalBuys, Lotto lastWeekLotto, BonusNumber bonusNumber) {
        this.totalBuys = totalBuys;
        this.lastWeekLotto = lastWeekLotto;
        this.bonusNumber = bonusNumber;
    }

    public DrawService(String lottoInput, String bonusNumberInput, LottoBuyList totalBuys) {
        Lotto lastWeekLotto = LottoParser.parseStringToLotto(lottoInput);
        BonusNumber bonusNumber = new BonusNumber(bonusNumberInput, lastWeekLotto);

        this.totalBuys = totalBuys;
        this.lastWeekLotto = lastWeekLotto;
        this.bonusNumber = bonusNumber;
    }

    public LottoResult createResult() {
        WinningLotto winningLotto = new WinningLotto(lastWeekLotto, bonusNumber);
        return new LottoResult(totalBuys, winningLotto);
    }
}
