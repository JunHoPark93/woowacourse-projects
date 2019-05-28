package com.woowacourse.lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final int LOTTO_COMPOSITION_NUMBER = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(LottoGenerator generator) {
        lottoNumbers = new ArrayList<>();
        initLotto(generator);
    }

    private void initLotto(LottoGenerator generator) {
        while (lottoNumbers.size() < LOTTO_COMPOSITION_NUMBER) {
            initSingleLottoNumber(generator);
        }
    }

    private void initSingleLottoNumber(LottoGenerator generator) {
        int num = generator.generateNumber();
        if (containsNumber(num)) {
            return;
        }
        lottoNumbers.add(new LottoNumber(num));
    }

    private boolean containsNumber(int num) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumbers -> lottoNumbers.getLottoNum() == num);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lotto lotto = (Lotto) o;

        return lottoNumbers != null ? lottoNumbers.equals(lotto.lottoNumbers) : lotto.lottoNumbers == null;
    }

    @Override
    public int hashCode() {
        return lottoNumbers != null ? lottoNumbers.hashCode() : 0;
    }
}
