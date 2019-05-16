package com.woowacourse.laddergame.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private List<Player> players;

    public Players() {
        players = new ArrayList<>();
    }

    public void add(Player player) {
        players.add(player);
    }

    public boolean isContains(String name) {
        return players.contains(new Player(name));
    }

    public int getPlayerNo(String name) {
        // 사다리의 번호는 1부터 시작한다
        return players.indexOf(new Player(name)) + 1;
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
