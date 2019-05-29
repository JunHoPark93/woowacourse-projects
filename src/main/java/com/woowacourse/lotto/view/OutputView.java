package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.LottoResult;

public class OutputView {

    public static void printLottoBuyList(LottoBuyList lottoBuyList) {
        System.out.println(lottoBuyList.getLottoSize() + "개를 구매했습니다");
        lottoBuyList.printLottoList();
    }

    public static void printLottoResult(LottoResult lottoResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        lottoResult.printResult();
    }
}
