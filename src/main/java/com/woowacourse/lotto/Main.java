package com.woowacourse.lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.service.LottoService;
import com.woowacourse.lotto.view.InputView;
import com.woowacourse.lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        PurchaseMoney purchaseMoney = InputView.getMoneyFromUser();
        LottoBuyList lottoBuyList = LottoService.getLottoBuyList(purchaseMoney);
        OutputView.printLottoBuyList(lottoBuyList);

        Lotto lastWeekLotto = InputView.getLastWeekLottoFromUser();
        LottoNumber bonusNumber = InputView.getBonusNumberFromUser(lastWeekLotto);

        WinningLotto winningLotto = new WinningLotto(lastWeekLotto, bonusNumber);

        LottoResult lottoResult = LottoService.getLottoResult(lottoBuyList, winningLotto);

        OutputView.printLottoResult(lottoResult, purchaseMoney);
    }
}
