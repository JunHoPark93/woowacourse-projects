package com.woowacourse.lotto.domain;

public class LottoNumber {
    private final int MIN_LOTTO_NUM = 1;
    private final int MAX_LOTTO_NUM = 45;

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
}
