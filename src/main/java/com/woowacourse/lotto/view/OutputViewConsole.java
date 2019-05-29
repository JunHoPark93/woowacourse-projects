package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.domain.PurchaseMoney;

public class OutputViewConsole implements OutputView{
    @Override
    public void printLottoBuyList(LottoBuyList lottoBuyList) {
        System.out.println(lottoBuyList.getLottoSize() + "개를 구매했습니다");
        lottoBuyList.printLottoList();
    }

    @Override
    public void printLottoResult(LottoResult lottoResult, PurchaseMoney purchaseMoney) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        lottoResult.printResult();
        System.out.println("총 수익률은 " + lottoResult.getProfitRatio(purchaseMoney)+"%입니다");
    }
}
