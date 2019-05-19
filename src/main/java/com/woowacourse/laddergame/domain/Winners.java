package com.woowacourse.laddergame.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Winners {
    private final HashMap<Player, Result> winners;

    public Winners(HashMap<Player, Result> winners) {
        this.winners = winners;
    }

//    public void add(Player player, Result result) {
//        winners.put(player, result);
//    }

    public String getSingleResult(String name) {
        return winners.get(new Player(name)).getResult();
    }

    public String getAllResult() {
        StringBuilder sb = new StringBuilder();
        for (Player player : winners.keySet()) {
            sb.append(player.getName());
            sb.append(" : ");
            sb.append(winners.get(player).getResult()).append("\n");
        }
        return sb.toString();
    }

    public boolean isContains(String name) {
        return winners.containsKey(new Player(name));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Winners winners1 = (Winners) o;

        return winners != null ? winners.equals(winners1.winners) : winners1.winners == null;
    }

    @Override
    public int hashCode() {
        return winners != null ? winners.hashCode() : 0;
    }
}
