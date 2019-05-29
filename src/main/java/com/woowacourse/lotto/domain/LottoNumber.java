package com.woowacourse.lotto.domain;

public class LottoNumber implements Comparable<LottoNumber> {
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

    public boolean isMatch(LottoNumber lottoNumber) {
        return lottoNum == lottoNumber.getLottoNum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LottoNumber that = (LottoNumber) o;

        if (MIN_LOTTO_NUM != that.MIN_LOTTO_NUM) return false;
        if (MAX_LOTTO_NUM != that.MAX_LOTTO_NUM) return false;
        return lottoNum == that.lottoNum;
    }

    @Override
    public int hashCode() {
        int result = MIN_LOTTO_NUM;
        result = 31 * result + MAX_LOTTO_NUM;
        result = 31 * result + lottoNum;
        return result;
    }

    @Override
    public int compareTo(LottoNumber o) {
        if (this.lottoNum > o.lottoNum) {
            return 1;
        }

        if (this.lottoNum < o.lottoNum) {
            return -1;
        }

        return 0;
    }
}