package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.*;

import java.util.ArrayList;
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
        Map<Rank, Integer> result = lottoResult.getResult();
        List<Rank> keys = new ArrayList<>(result.keySet());
        for (int i = keys.size() - 1; i >= 0; i--) {
            keys.get(i).printRank(result.get(keys.get(i)));
        }
        System.out.println("총 수익률은 " + lottoResult.getProfitRatio(purchaseMoney)+"%입니다");
    }
}
