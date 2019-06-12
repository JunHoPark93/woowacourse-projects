package com.woowacourse.lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.service.ConsoleLottoService;
import com.woowacourse.lotto.view.InputViewConsole;
import com.woowacourse.lotto.view.OutputViewConsole;

public class Main {
    private static ConsoleLottoService consoleLottoService = new ConsoleLottoService();

    public static void main(String[] args) {
        PurchaseMoney purchaseMoney = InputViewConsole.inputPurchaseMoney();
        ManualNumber manualNumber = InputViewConsole.inputManualNumber(purchaseMoney);
        LottoBuyList manualBuys = InputViewConsole.inputManualBuys(manualNumber);
        LottoBuyList totalBuys = consoleLottoService.createTotalBuyList(manualBuys, purchaseMoney, manualNumber);

        OutputViewConsole.printLottoBuyList(totalBuys);

        Lotto lastWeekLotto = InputViewConsole.inputLastWeekLotto();
        LottoNumber bonusNumber = InputViewConsole.inputBonusNumber(lastWeekLotto);
        LottoResult lottoResult = consoleLottoService.createResult(totalBuys, lastWeekLotto, bonusNumber);

        OutputViewConsole.printLottoResult(lottoResult, purchaseMoney);
    }
}
