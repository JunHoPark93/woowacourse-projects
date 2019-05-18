package com.woowacourse.laddergame.domain.vo;

import com.woowacourse.laddergame.domain.Winners;

public class WinnerVO {
    private Winners winners;

    public WinnerVO(Winners winners) {
        this.winners = winners;
    }

    public String getSingleResult(String name) {
        return winners.getSingleResult(name);
    }

    public String getAllResult() {
        return winners.getAllResult();
    }

    public boolean isContains(String name) {
        return winners.isContains(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WinnerVO winnerVO = (WinnerVO) o;

        return winners != null ? winners.equals(winnerVO.winners) : winnerVO.winners == null;
    }

    @Override
    public int hashCode() {
        return winners != null ? winners.hashCode() : 0;
    }
}
