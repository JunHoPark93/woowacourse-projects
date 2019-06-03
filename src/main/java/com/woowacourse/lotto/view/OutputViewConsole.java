package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OutputViewConsole implements OutputView{
    @Override
    public void printLottoBuyList(LottoBuyList lottoBuyList) {
        System.out.println(lottoBuyList.getLottoSize() + "개를 구매했습니다");
        List<Lotto> lottos = lottoBuyList.getLottoBuyList();
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    @Override
    public void printLottoResult(LottoResult lottoResult, PurchaseMoney purchaseMoney) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        Iterator<Map.Entry<Rank, Integer>> iterator = lottoResult.getIterator();

        while (iterator.hasNext()) {
            Map.Entry<Rank, Integer> key = iterator.next();
            key.getKey().printRank(key.getValue());
        }

        System.out.println("총 수익률은 " + lottoResult.getProfitRatio(purchaseMoney)+"%입니다");
    }
}
