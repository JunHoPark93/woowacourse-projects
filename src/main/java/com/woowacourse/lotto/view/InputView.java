package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.*;

public interface InputView {
    PurchaseMoney inputMoney();
    ManualNumber inputManualNumber(PurchaseMoney purchaseMoney);
    Lotto inputLastWeekLotto();
    LottoNumber inputBonusNumber(Lotto lastWeekLotto);
    LottoBuyList inputManualLotto(ManualNumber manualNumber);
}
