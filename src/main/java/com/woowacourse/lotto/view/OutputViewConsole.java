package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.domain.PurchaseMoney;

public class OutputViewConsole {
    public static String inputPurchaseMoneyMsg() {
        return "구입금액을 입력해 주세요.";
    }

    public static String inputManualNumberMsg() {
        return "수동으로 구매할 로또 수를 입력해 주세요.";
    }

    public static void printLottoBuyList(LottoBuyList lottoBuyList) {
        System.out.println(lottoBuyList.size() + "개를 구매했습니다");
        for (int i = 0; i < lottoBuyList.size(); i++) {
            System.out.println(lottoBuyList.getLotto(i));
        }
    }

    public static void printLottoResult(LottoResult lottoResult, PurchaseMoney purchaseMoney) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(OutputMessageConverter.makeHittingStatusMsg(lottoResult));
        System.out.println(OutputMessageConverter.makeProfitMsg(lottoResult.calculateProfitRatio(purchaseMoney)));
    }
}
