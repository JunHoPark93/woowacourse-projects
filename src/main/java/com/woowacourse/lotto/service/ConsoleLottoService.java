package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.*;

public class ConsoleLottoService extends LottoService {
    @Override
    public LottoBuyList createTotalBuyList(LottoBuyList manualBuys, PurchaseMoney purchaseMoney, ManualNumber manualNumber) {
        LottoBuyList autoBuys = super.getAutoLottoBuyList(purchaseMoney, manualNumber);
        return autoBuys.joinBuyList(manualBuys);
    }

    @Override
    public LottoResult createResult(LottoBuyList totalBuys, WinningLotto winningLotto) {
        return new LottoResult(totalBuys, winningLotto);
    }
}
