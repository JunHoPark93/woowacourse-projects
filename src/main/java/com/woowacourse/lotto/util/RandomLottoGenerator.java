package com.woowacourse.lotto.util;

import java.util.Random;

public class RandomLottoGenerator implements LottoGenerator {
    private static final int MAX_LOTTO_NUM = 45;
    private static final Random RANDOM = new Random();

    @Override
    public int generateNumber() {
        return RANDOM.nextInt(MAX_LOTTO_NUM - 1) + 1;
    }
}
