package com.woowacourse.lotto.util;

import com.woowacourse.lotto.domain.LottoNumber;

import java.util.*;

public class RandomLottoGenerator implements LottoGenerator {
    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;

    private static final Stack<LottoNumber> lottoNumbers = new Stack<>();

    static {
        for (int i = MIN_LOTTO_NUM; i < MAX_LOTTO_NUM; i++) {
            lottoNumbers.push(new LottoNumber(i));
        }
    }

    @Override
    public LottoNumber generateNumber() {
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.pop();
    }
}
