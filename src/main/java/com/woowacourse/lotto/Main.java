package com.woowacourse.lotto;

import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.PurchaseMoney;
import com.woowacourse.lotto.service.LottoService;
import com.woowacourse.lotto.view.InputView;
import com.woowacourse.lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        PurchaseMoney purchaseMoney = InputView.getMoneyFromUser();
        LottoBuyList lottoBuyList = LottoService.getLottoBuyList(purchaseMoney);
        OutputView.printLottoBuyList(lottoBuyList);
    }

}
