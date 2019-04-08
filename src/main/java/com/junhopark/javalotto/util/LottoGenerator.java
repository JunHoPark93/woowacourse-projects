/*
 * Class:   LottoGenerator
 *
 * Version: 1.0.0
 *
 * Date:    2019-04-06
 *
 * Author:  Jun Ho Park
 *
 */

package com.junhopark.javalotto.util;

import com.junhopark.javalotto.domain.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LottoGenerator {
    private static final int MAX_LOTTO_NUM = 45;
    private static final int LOTTO_NUM = 6;

    public static Lotto generateLotto() {
        HashSet<Integer> lottoNumberSet = new HashSet<>();
        while (lottoNumberSet.size() < LOTTO_NUM) {
            int randomNum = ThreadLocalRandom.current().nextInt(MAX_LOTTO_NUM) + 1;
            lottoNumberSet.add(randomNum);
        }
        List<Integer> list = new ArrayList<>(lottoNumberSet);
        Collections.sort(list);
        return new Lotto(list);
    }
}
