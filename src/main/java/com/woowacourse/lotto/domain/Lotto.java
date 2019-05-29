package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.util.LottoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private static final int LOTTO_COMPOSITION_NUMBER = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(LottoGenerator generator) {
        lottoNumbers = new ArrayList<>();
        initLotto(generator);
    }

    private void initLotto(LottoGenerator generator) {
        while (lottoNumbers.size() < LOTTO_COMPOSITION_NUMBER) {
            initSingleLottoNumber(generator);
        }
        Collections.sort(lottoNumbers);
    }

    private void initSingleLottoNumber(LottoGenerator generator) {
        LottoNumber lottoNumber = new LottoNumber(generator.generateNumber());
        if (contains(lottoNumber)) {
            return;
        }
        lottoNumbers.add(lottoNumber);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumbers -> lottoNumbers.getLottoNum() == lottoNumber.getLottoNum());
    }

    public int matchCount(Lotto lotto) {
        return (int) lottoNumbers.stream()
                .filter(lotto::contains)
                .count();
    }

    public boolean isBonusMatch(LottoNumber bonusNumber) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.isMatch(bonusNumber));
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

    @Override
    public String toString() {
        String lotto = lottoNumbers.stream()
                .map(LottoNumber::getLottoNum)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(lotto);
        sb.append("]");

        return sb.toString();
    }
}
