package com.woowacourse.lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.service.LottoService;
import com.woowacourse.lotto.view.InputView;
import com.woowacourse.lotto.view.InputViewConsole;
import com.woowacourse.lotto.view.OutputView;
import com.woowacourse.lotto.view.OutputViewConsole;

public class Main {
    public static void main(String[] args) {
        InputView inputViewConsole = new InputViewConsole();
        OutputView outputViewConsole = new OutputViewConsole();

        PurchaseMoney purchaseMoney = inputViewConsole.getMoneyFromUser();
        ManualNumber manualNumber = inputViewConsole.getManualNumberFromUser(purchaseMoney);

        LottoBuyList manualBuyList = inputViewConsole.getManualLottoFromUser(manualNumber);
        LottoBuyList autoBuyList = LottoService.getAutoLottoBuyList(purchaseMoney, manualNumber);
        LottoBuyList totalBuyList = LottoService.joinBuyList(manualBuyList, autoBuyList);

        outputViewConsole.printLottoBuyList(totalBuyList);

        Lotto lastWeekLotto = inputViewConsole.getLastWeekLottoFromUser();
        LottoNumber bonusNumber = inputViewConsole.getBonusNumberFromUser(lastWeekLotto);
        LottoResult lottoResult = LottoService.getLottoResult(totalBuyList, new WinningLotto(lastWeekLotto, bonusNumber));

        outputViewConsole.printLottoResult(lottoResult, purchaseMoney);
    }
}
