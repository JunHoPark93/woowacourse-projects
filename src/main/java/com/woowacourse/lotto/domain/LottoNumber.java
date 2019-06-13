package com.woowacourse.lotto.domain;

public class LottoNumber implements Comparable<LottoNumber>, Number {
    private static final int MIN_LOTTO_NUM = 1;
    private static final int MAX_LOTTO_NUM = 45;

    private final int lottoNum;

    public LottoNumber(int lottoNum) {
        checkLottoRange(lottoNum);
        this.lottoNum = lottoNum;
    }

    private void checkLottoRange(int lottoNum) {
        if (lottoNum < MIN_LOTTO_NUM || lottoNum > MAX_LOTTO_NUM) {
            throw new IllegalArgumentException("로또 숫자 범위는 1~45 입니다");
        }
    }

    public int getLottoNum() {
        return lottoNum;
    }

    public boolean isMatch(Number lottoNumber) {
        return lottoNum == lottoNumber.value();
    }

    @Override
    public int value() {
        return lottoNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LottoNumber that = (LottoNumber) o;

        return lottoNum == that.lottoNum;
    }

    @Override
    public int hashCode() {
        return lottoNum;
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(this.lottoNum, o.lottoNum);
    }

    @Override
    public String toString() {
        return "LottoNumber{" +
                "lottoNum=" + lottoNum +
                '}';
    }
}
