package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.*;

public interface InputView {
    PurchaseMoney getMoneyFromUser();
    ManualNumber getManualNumberFromUser(PurchaseMoney purchaseMoney);
    Lotto getLastWeekLottoFromUser();
    LottoNumber getBonusNumberFromUser(Lotto lastWeekLotto);
    LottoBuyList getManualLottoFromUser(ManualNumber manualNumber);
}
