package com.woowacourse.lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.service.LottoService;
import com.woowacourse.lotto.view.InputView;
import com.woowacourse.lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        PurchaseMoney purchaseMoney = InputView.getMoneyFromUser();
        ManualNumber manualNumber = InputView.getManualNumberFromUser(purchaseMoney);

        LottoBuyList manualBuyList = InputView.getManualLottoFromUser(manualNumber);
        LottoBuyList autoBuyList = LottoService.getAutoLottoBuyList(purchaseMoney, manualNumber);
        LottoBuyList totalBuyList = LottoService.joinBuyList(manualBuyList, autoBuyList);

        OutputView.printLottoBuyList(totalBuyList);

        Lotto lastWeekLotto = InputView.getLastWeekLottoFromUser();
        LottoNumber bonusNumber = InputView.getBonusNumberFromUser(lastWeekLotto);
        LottoResult lottoResult = LottoService.getLottoResult(totalBuyList, new WinningLotto(lastWeekLotto, bonusNumber));

        OutputView.printLottoResult(lottoResult, purchaseMoney);
    }
}
