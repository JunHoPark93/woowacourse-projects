package com.woowacourse.lotto.domain;

import com.woowacourse.lotto.view.OutputMessageConverter;

import java.util.List;

public class History {
    private LottoBuyList lottoBuyList;
    private LottoTicket winningTicket;
    private LottoNumber bonusNumber;
    private LottoResult lottoResult;
    private PurchaseMoney purchaseMoney;

    public History(LottoBuyList lottoBuyList, LottoResult lottoResult, PurchaseMoney purchaseMoney, LottoTicket winningTicket, LottoNumber bonusNumber) {
        this.lottoBuyList = lottoBuyList;
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
        this.lottoResult = lottoResult;
        this.purchaseMoney = purchaseMoney;
    }

    public double price() {
        return lottoBuyList.price();
    }

    public double profitRatio() {
        return lottoResult.calculateProfitRatio(purchaseMoney);
    }

    public String resultMsg() {
        return OutputMessageConverter.makeHittingStatusMsg(lottoResult);
    }

    public List<LottoTicket> tickets() {
        return lottoBuyList.getLottoTickets();
    }

    public double winningMoney() {
        return lottoResult.calculateWinningMoney();
    }

    public LottoTicket getWinningTicket() {
        return winningTicket;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
