package com.woowacourse.laddergame.domain.vo;

import java.util.HashMap;

public class WinnerVO {
    private HashMap<String, String> winners;

    public WinnerVO(HashMap<String, String> winners) {
        this.winners = winners;
    }

    public String getSingleResult(String name) {
        return winners.get(name);
    }

    public String getAllResult() {
        StringBuilder sb = new StringBuilder();
        for (String name : winners.keySet()) {
            sb.append(name);
            sb.append(" : ");
            sb.append(winners.get(name)).append("\n");
        }
        return sb.toString();
    }

    public boolean isContains(String name) {
        return winners.containsKey(name);
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
