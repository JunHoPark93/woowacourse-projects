package com.woowacourse.lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.service.LottoService;
import com.woowacourse.lotto.view.InputView;
import com.woowacourse.lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        PurchaseMoney purchaseMoney = InputView.getMoneyFromUser();
        ManualNumber manualNumber = InputView.getManualNumberFromUser(purchaseMoney);
        // TODO lottobuylist를 처음에 수동 구매 함수로 가져온다 (manualBuyList)
        LottoBuyList manualBuyList = InputView.getManualLottoFromUser(manualNumber);

        // TODO 그리고 남은돈으로 lottoBuyList 만들고 (autoBuyList) 두 buyList를 하나로 합쳐버린다 (lottoBuyList). get, get하고 concat
        LottoBuyList autoBuyList = LottoService.getAutoLottoBuyList(new PurchaseMoney(purchaseMoney.getMoney() - manualNumber.getTotalPrice()));

        LottoBuyList totalBuyList = LottoService.joinBuyList(manualBuyList, autoBuyList);
        OutputView.printLottoBuyList(totalBuyList);

        Lotto lastWeekLotto = InputView.getLastWeekLottoFromUser();
        LottoNumber bonusNumber = InputView.getBonusNumberFromUser(lastWeekLotto);

        WinningLotto winningLotto = new WinningLotto(lastWeekLotto, bonusNumber);

        LottoResult lottoResult = LottoService.getLottoResult(totalBuyList, winningLotto);

        OutputView.printLottoResult(lottoResult, purchaseMoney);
    }
}
