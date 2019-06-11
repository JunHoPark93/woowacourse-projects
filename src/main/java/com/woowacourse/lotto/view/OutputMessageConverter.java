package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.LottoResult;
import com.woowacourse.lotto.domain.Rank;

import java.util.Iterator;
import java.util.Map;

public class OutputMessageConverter {
    public static String makeHittingStatusMsg(LottoResult lottoResult) {
        Iterator<Map.Entry<Rank, Integer>> iterator = lottoResult.getIterator();
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<Rank, Integer> key = iterator.next();
            Rank rank = key.getKey();

            if (rank.getMoney() == Rank.NONE.getMoney()) {
                continue;
            }
            if (rank.getMoney() == Rank.SECOND.getMoney()) {
                sb.append(rank.getMatchCount())
                        .append("개 일치, 보너스 볼 일치(")
                        .append(rank.getMoney())
                        .append("원)-")
                        .append(key.getValue())
                        .append("개")
                        .append("\n");
                continue;
            }
            sb.append(rank.getMatchCount())
                    .append("개 일치")
                    .append("(")
                    .append(rank.getMoney())
                    .append("원)")
                    .append("-")
                    .append(key.getValue())
                    .append("개")
                    .append("\n");
        }

        return sb.toString();
    }

    public static String makeProfitMsg(double calculateProfitRatio) {
        StringBuilder sb = new StringBuilder();
        sb.append("총 수익률은 ");
        sb.append(String.format("%.1f", calculateProfitRatio));
        sb.append("%입니다");
        return sb.toString();
    }
}
