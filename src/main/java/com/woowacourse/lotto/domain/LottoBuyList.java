package com.woowacourse.lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoBuyList {
    private final List<Lotto> lottoBuyList;

    public LottoBuyList(List<Lotto> lottoBuyList) {
        this.lottoBuyList = lottoBuyList;
    }

    public int getLottoSize() {
        return lottoBuyList.size();
    }

    public void printLottoList() {
        for(Lotto lotto : lottoBuyList) {
            System.out.println(lotto);
        }
    }

    public List<Lotto> getLottoBuyList() {
        return lottoBuyList;
    }

    public LottoBuyList joinBuyList(LottoBuyList lottoBuyList) {
        List<Lotto> joinedBuyList = new ArrayList<>(this.lottoBuyList);
        joinedBuyList.addAll(lottoBuyList.getLottoBuyList());
        return new LottoBuyList(joinedBuyList);
    }
}
