package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.LottoBuyList;

public class OutputView {

    public static void printLottoBuyList(LottoBuyList lottoBuyList) {
        System.out.println(lottoBuyList.getLottoSize() + "개를 구매했습니다");
        lottoBuyList.printLottoList();
    }
}
