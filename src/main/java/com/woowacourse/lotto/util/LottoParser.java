package com.woowacourse.lotto.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoParser {
    public static LottoGenerator parseLottoGenerator(String lottoInput) {
        String[] tokens = lottoInput.split(",");
        List<Integer> list = Arrays.stream(tokens)
                .map(String::trim)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        return new IntendedLottoGenerator(list);
    }
}
