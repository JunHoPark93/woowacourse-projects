package com.woowacourse.laddergame.domain;

public class MadeLadder {
    private Players players;
    private Ladder ladder;
    private Results results;

    public MadeLadder(Players players, Ladder ladder, Results results) {
        this.players = players;
        this.ladder = ladder;
        this.results = results;
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

    public String getLadderResult() {
        return results.toString();
    }
}
