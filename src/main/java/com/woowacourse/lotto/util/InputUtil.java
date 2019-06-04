package com.woowacourse.lotto.util;

import com.woowacourse.lotto.domain.Lotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputUtil {
    public static Lotto parseStringToLotto(String input) {
        String[] tokens = input.split(",");

        List<Integer> lottoNumbers = Arrays.stream(tokens)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        IntendedLottoGenerator intendedLottoGenerator = new IntendedLottoGenerator(lottoNumbers);

        return new Lotto(intendedLottoGenerator);
    }
}
