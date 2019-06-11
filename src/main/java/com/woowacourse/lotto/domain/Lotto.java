package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.util.LottoGenerator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    public static final int PRICE = 1000;
    public static final int MIN_NUM = 1;
    public static final int MAX_NUM = 45;
    public static final int COMPOSITE_NUM = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(LottoGenerator generator) {
        lottoNumbers = generator.generateNumber();
        Collections.sort(lottoNumbers);
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumbers -> lottoNumbers.isMatch(lottoNumber));
    }

    public int matchCount(Lotto lotto) {
        return (int) lottoNumbers.stream()
                .filter(lotto::contains)
                .count();
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
        sb.append(lotto);

        return sb.toString();
    }
}
