package com.woowacourse.laddergame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class PlayersTest {
    @Test
    void player_인덱스_반환() {
        Players players = new Players();
        players.add(new Player("pobi"));
        players.add(new Player("aiden"));
        players.add(new Player("jay"));

        assertThat(players.getPlayerNo("aiden")).isEqualTo(2);
    }
}