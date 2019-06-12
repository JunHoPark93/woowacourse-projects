package com.woowacourse.lotto.domain.dto;

import com.woowacourse.lotto.domain.LottoTicket;

import java.util.List;

public class LottoBuyDto {
    private List<LottoTicket> tickets;

    public LottoBuyDto(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public List<LottoTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }
}
