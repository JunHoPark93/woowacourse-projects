package com.woowacourse.lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.service.LottoService;
import com.woowacourse.lotto.view.InputViewConsole;
import com.woowacourse.lotto.view.OutputViewConsole;

public class Main {
    public static void main(String[] args) {
        PurchaseMoney purchaseMoney = InputViewConsole.inputPurchaseMoney();
        ManualNumber manualNumber = InputViewConsole.inputManualNumber(purchaseMoney);
        LottoBuyList manualBuys = InputViewConsole.inputManualBuys(manualNumber);
        LottoBuyList totalBuys = LottoService.createTotalBuyList(manualBuys, purchaseMoney, manualNumber);

        OutputViewConsole.printLottoBuyList(totalBuys);

        Lotto lastWeekLotto = InputViewConsole.inputLastWeekLotto();
        LottoNumber bonusNumber = InputViewConsole.inputBonusNumber(lastWeekLotto);
        LottoResult lottoResult = LottoService.createResult(totalBuys, new WinningLotto(lastWeekLotto, bonusNumber));

        OutputViewConsole.printLottoResult(lottoResult, purchaseMoney);
    }
}
