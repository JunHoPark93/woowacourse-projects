package com.woowacourse.lotto.util;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoNumber;

import java.util.*;

public class RandomLottoGenerator implements LottoGenerator {
    private static final int LOTTO_START_IDX = 0;
    private static final int LOTTO_END_IDX = 6;

    private static List<LottoNumber> lottoNumbers = new ArrayList<>();

    static {
        for (int i = Lotto.MIN_NUM; i <= Lotto.MAX_NUM; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    @Override
    public List<LottoNumber> generateNumber() {
        Collections.shuffle(lottoNumbers);
        return new ArrayList<>(lottoNumbers.subList(LOTTO_START_IDX, LOTTO_END_IDX));
    }
}
