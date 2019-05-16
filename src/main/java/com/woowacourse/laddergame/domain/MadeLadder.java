package com.woowacourse.laddergame.domain;

public class MadeLadder {
    private Players players;
    private Ladder ladder;

    public MadeLadder(Players players, Ladder ladder) {
        this.players = players;
        this.ladder = ladder;
    }

    public Players getPlayers() {
        return players;
    }

    public String getLadderShape() {
        return ladder.toString();
    }

    public String getPlayerNames() {
        return players.toString();
    }
}
