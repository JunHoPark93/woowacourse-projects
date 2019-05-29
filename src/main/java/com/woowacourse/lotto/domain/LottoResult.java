package com.woowacourse.lotto.domain;

import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> map;

    public LottoResult(Map<Rank, Integer> map) {
        this.map = map;
    }

    public Map<Rank, Integer> getMap() {
        return map;
    }
}
