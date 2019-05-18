package com.woowacourse.laddergame.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Winners {
    private HashMap<String, String> winners;

    public Winners() {
        this.winners = new LinkedHashMap<>();
    }

    public void add(String playerName, Result result) {
        winners.put(playerName, result.getResult());
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
}
