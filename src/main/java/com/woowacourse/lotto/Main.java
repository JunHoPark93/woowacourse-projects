package com.woowacourse.lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.service.DrawConsoleService;
import com.woowacourse.lotto.service.LottoBuyConsoleService;
import com.woowacourse.lotto.view.InputViewConsole;
import com.woowacourse.lotto.view.OutputViewConsole;

public class Main {
    private static InputViewConsole inputViewConsole = new InputViewConsole();
    private static LottoBuyConsoleService lottoBuyConsoleService;
    private static DrawConsoleService drawService;

    public static void main(String[] args) {
        PurchaseMoney purchaseMoney = inputViewConsole.inputPurchaseMoney();
        ManualNumber manualNumber = inputViewConsole.inputManualNumber(purchaseMoney);
        LottoBuyList manualBuys = inputViewConsole.inputManualBuys(manualNumber);

        lottoBuyConsoleService = new LottoBuyConsoleService(manualNumber, purchaseMoney, manualBuys);
        LottoBuyList totalBuys = lottoBuyConsoleService.createTotalBuyList();

        OutputViewConsole.printLottoBuyList(totalBuys);

        Lotto lastWeekLotto = inputViewConsole.inputLastWeekLotto();
        BonusNumber bonusNumber = inputViewConsole.inputBonusNumber(lastWeekLotto);

        drawService = new DrawConsoleService(totalBuys, lastWeekLotto, bonusNumber);
        LottoResult lottoResult = drawService.createResult();

        OutputViewConsole.printLottoResult(lottoResult, purchaseMoney);
    }
}
