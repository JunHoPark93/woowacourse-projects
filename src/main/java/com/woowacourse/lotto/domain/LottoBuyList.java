package com.woowacourse.lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class LottoBuyList {
    private final List<Lotto> lottoBuyList;

    public LottoBuyList(List<Lotto> lottoBuyList) {
        this.lottoBuyList = lottoBuyList;
    }

    public int size() {
        return lottoBuyList.size();
    }

    public LottoTicket getLotto(int index) {
        return lottoBuyList.get(index);
    }

    public List<LottoTicket> getLottoTickets() {
        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            tickets.add(this.lottoBuyList.get(i));
        }
        return tickets;
    }

    public LottoBuyList joinBuyList(LottoBuyList lottoBuyList) {
        List<Lotto> joinedBuyList = new ArrayList<>(this.lottoBuyList);
        joinedBuyList.addAll(lottoBuyList.getLottoBuyList());
        return new LottoBuyList(joinedBuyList);
    }

    public void addManualLotto(Lotto lotto) {
        lottoBuyList.add(lotto);
    }

    private List<Lotto> getLottoBuyList() {
        return lottoBuyList;
    }

    public double price() {
        return size() * Lotto.PRICE;
    }
}
