package com.woowacourse.laddergame.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private List<Player> players;

    public Players() {
        players = new ArrayList<>();
    }

    public void add(Player player) {
        if (checkDuplicateName(player)) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다");
        }
        players.add(player);
    }

    public void addAll(List<Player> players) {
        this.players = players;
    }

    private boolean checkDuplicateName(Player player) {
        return players.contains(player);
    }

    public int getPlayerNo(String name) {
        // 사다리의 번호는 1부터 시작한다
        return players.indexOf(new Player(name)) + 1;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public List<String> getPlayersName() {
        return players.stream().map(Player::getName).collect(Collectors.toList());
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
