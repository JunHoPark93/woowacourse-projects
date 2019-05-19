package com.woowacourse.laddergame.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        checkDuplicateName(players);
        this.players = players;
    }

    private void checkDuplicateName(List<Player> players) {
        Set<Player> nameSet = new HashSet<>(players);
        if (nameSet.size() != players.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다");
        }
    }

    public int getPlayerNo(String name) {
        // 사다리의 번호는 1부터 시작한다
        return players.indexOf(new Player(name)) + 1;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public List<String> getPlayersName() {
        return players.stream().map(Player::getName).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Players players1 = (Players) o;

        return players != null ? players.equals(players1.players) : players1.players == null;
    }

    @Override
    public int hashCode() {
        return players != null ? players.hashCode() : 0;
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
