package com.woowacourse.lotto.domain.dto;

import com.woowacourse.lotto.domain.BonusNumber;
import com.woowacourse.lotto.domain.LottoTicket;

import java.util.List;

public class HistoryDto {
    private List<LottoTicket> tickets;
    private LottoTicket winningTicket;
    private BonusNumber bonusNumber;
    private String resultMsg;
    private String profitRatio;
    private String winningMoney;

    public List<LottoTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public LottoTicket getWinningTicket() {
        return winningTicket;
    }

    public void setWinningTicket(LottoTicket winningTicket) {
        this.winningTicket = winningTicket;
    }

    public BonusNumber getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(BonusNumber bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(String profitRatio) {
        this.profitRatio = profitRatio;
    }

    public String getWinningMoney() {
        return winningMoney;
    }

    public void setWinningMoney(String winningMoney) {
        this.winningMoney = winningMoney;
    }
}
