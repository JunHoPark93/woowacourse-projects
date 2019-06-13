package com.woowacourse.lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.service.DrawService;
import com.woowacourse.lotto.service.LottoBuyService;
import com.woowacourse.lotto.view.InputViewConsole;
import com.woowacourse.lotto.view.OutputViewConsole;

public class Main {
    private static InputViewConsole inputViewConsole = new InputViewConsole();
    private static LottoBuyService lottoBuyService;
    private static DrawService drawService;

    public static void main(String[] args) {
        PurchaseMoney purchaseMoney = inputViewConsole.inputPurchaseMoney();
        ManualNumber manualNumber = inputViewConsole.inputManualNumber(purchaseMoney);
        LottoBuyList manualBuys = inputViewConsole.inputManualBuys(manualNumber);

        lottoBuyService = new LottoBuyService(manualNumber, purchaseMoney, manualBuys);
        LottoBuyList totalBuys = lottoBuyService.createTotalBuyList();

        OutputViewConsole.printLottoBuyList(totalBuys);

        Lotto lastWeekLotto = inputViewConsole.inputLastWeekLotto();
        BonusNumber bonusNumber = inputViewConsole.inputBonusNumber(lastWeekLotto);

        drawService = new DrawService(totalBuys, lastWeekLotto, bonusNumber);
        LottoResult lottoResult = drawService.createResult();

        OutputViewConsole.printLottoResult(lottoResult, purchaseMoney);
    }
}
