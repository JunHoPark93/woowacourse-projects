package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.util.IntendedLottoGenerator;
import com.woowacourse.lotto.util.LottoGenerator;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class LottoBuyListTest {
    @Test
    void 로또_당첨_결과_반환() {
        // given
        LottoGenerator lottoGenerator1 = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 7));
        LottoGenerator lottoGenerator2 = new IntendedLottoGenerator(Arrays.asList(4, 5, 6, 7, 8, 9));
        LottoGenerator winningGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 6));

        Lotto lastWeekLotto = new Lotto(winningGenerator);
        List<Lotto> lottoList = Arrays.asList(new Lotto(lottoGenerator1), new Lotto(lottoGenerator2));

        LottoBuyList lottoBuyList = new LottoBuyList(lottoList);
        WinningLotto winningLotto = new WinningLotto(lastWeekLotto, new LottoNumber(7));

        LottoResult lottoResult = new LottoResult(lottoBuyList, winningLotto);
        Iterator<Map.Entry<Rank, Integer>> iterator = lottoResult.getIterator();

        // when
        double sum = 0.0;
        while (iterator.hasNext()) {
            Map.Entry<Rank, Integer> key = iterator.next();
            Rank rank = key.getKey();
            sum += key.getValue() * rank.getMoney();
        }

        // then
        assertThat(sum).isEqualTo(30005000);
    }
}