package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.domain.PurchaseMoney;

public interface OutputView {
    void printLottoBuyList(LottoBuyList lottoBuyList);
    void printLottoResult(LottoResult lottoResult, PurchaseMoney purchaseMoney);
}
