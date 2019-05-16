package com.woowacourse.laddergame.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    List<Player> players;

    public Players() {
        players = new ArrayList<>();
    }

    public void add(Player player) {
        players.add(player);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Player player : players) {
            sb.append(player.toString());
        }
        return sb.toString();
    }
}
