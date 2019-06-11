package com.woowacourse.lotto.domain.dto;

import com.woowacourse.lotto.domain.Lotto;

import java.util.List;

public class LottoBuyResultDto {
    private List<Lotto> lottoBuyList;

    public List<Lotto> getLottoBuyList() {
        return lottoBuyList;
    }

    public void setLottoBuyList(List<Lotto> lottoBuyList) {
        this.lottoBuyList = lottoBuyList;
    }
}
