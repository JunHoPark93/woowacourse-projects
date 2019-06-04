package com.woowacourse.lotto.util;

import com.woowacourse.lotto.domain.LottoNumber;

import java.util.*;

public class RandomLottoGenerator implements LottoGenerator {
    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;
    private static final int LOTTO_START_IDX = 0;
    private static final int LOTTO_END_IDX = 6;

    private static List<LottoNumber> lottoNumbers = new ArrayList<>();

    static {
        for (int i = MIN_LOTTO_NUM; i <= MAX_LOTTO_NUM; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
    }

    @Override
    public List<LottoNumber> generateNumber() {
        Collections.shuffle(lottoNumbers);
        return new ArrayList<>(lottoNumbers.subList(LOTTO_START_IDX, LOTTO_END_IDX));
    }
}
