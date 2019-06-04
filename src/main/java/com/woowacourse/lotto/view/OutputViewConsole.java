package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OutputViewConsole implements OutputView {
    private static final int LOTTO_NONE_PLACE = 0;
    private static final int LOTTO_SECOND_PLACE = 30_000_000;

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
        printHittingStatus(lottoResult);

        System.out.print("총 수익률은 ");
        System.out.format("%.1f", lottoResult.getProfitRatio(purchaseMoney));
        System.out.println("%입니다");
    }

    private void printHittingStatus(LottoResult lottoResult) {
        Iterator<Map.Entry<Rank, Integer>> iterator = lottoResult.getIterator();
        while (iterator.hasNext()) {
            Map.Entry<Rank, Integer> key = iterator.next();
            Rank rank = key.getKey();

            if (rank.getMoney() == LOTTO_NONE_PLACE) {
                continue;
            }
            if (rank.getMoney() == LOTTO_SECOND_PLACE) {
                System.out.println(rank.getMatchCount() + "개 일치, 보너스 볼 일치" + "(" + rank.getMoney() + "원)" + "-" + key.getValue() + "개");
                continue;
            }
            System.out.println(rank.getMatchCount() + "개 일치" + "(" + rank.getMoney() + "원)" + "-" + key.getValue() + "개");
        }
    }
}
