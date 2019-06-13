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

    private HistoryDto() {
    }

    public static class Builder {
        private List<LottoTicket> tickets;
        private LottoTicket winningTicket;
        private BonusNumber bonusNumber;
        private String resultMsg;
        private String profitRatio;
        private String winningMoney;

        public Builder tickets(List<LottoTicket> tickets) {
            this.tickets = tickets;
            return this;
        }

        public Builder winningTicket(LottoTicket winningTicket) {
            this.winningTicket = winningTicket;
            return this;
        }

        public Builder bonusNumber(BonusNumber bonusNumber) {
            this.bonusNumber = bonusNumber;
            return this;
        }

        public Builder resultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
            return this;
        }

        public Builder profitRatio(String profitRatio) {
            this.profitRatio = profitRatio;
            return this;
        }

        public Builder winningMoney(String winningMoney) {
            this.winningMoney = winningMoney;
            return this;
        }

        public HistoryDto build() {
            HistoryDto historyDto = new HistoryDto();
            historyDto.tickets = this.tickets;
            historyDto.winningTicket = this.winningTicket;
            historyDto.winningMoney = this.winningMoney;
            historyDto.profitRatio = this.profitRatio;
            historyDto.resultMsg = this.resultMsg;
            historyDto.bonusNumber = this.bonusNumber;

            return historyDto;
        }
    }

    public List<LottoTicket> getTickets() {
        return tickets;
    }

    public LottoTicket getWinningTicket() {
        return winningTicket;
    }

    public BonusNumber getBonusNumber() {
        return bonusNumber;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public String getProfitRatio() {
        return profitRatio;
    }

    public String getWinningMoney() {
        return winningMoney;
    }
}
