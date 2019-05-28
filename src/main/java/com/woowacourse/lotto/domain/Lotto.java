package com.woowacourse.lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final int LOTTO_COMPOSITION_NUMBER = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        checkLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void checkLottoNumbers(List<LottoNumber> lottoNumbers) {
        checkLottoCompositionNumber(lottoNumbers);
        checkDuplicate(lottoNumbers);

    }

    private void checkLottoCompositionNumber(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_COMPOSITION_NUMBER) {
            throw new IllegalArgumentException("로또는 6개의 숫자로 구성되어야 합니다");
        }
    }

    private void checkDuplicate(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> set = new HashSet<>(lottoNumbers);
        if (set.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("중복된 로또 숫자입니다");
        }
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
