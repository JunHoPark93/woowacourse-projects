package com.woowacourse.laddergame.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class PlayersTest {
    @Test
    void player_인덱스_반환() {
        List<Player> playerList = Arrays.asList(new Player("pobi"),
                new Player("aiden"),
                new Player("jay"));

        Players players = new Players(playerList);

        assertThat(players.getPlayerNo("aiden")).isEqualTo(2);
    }

    @Test
    void 이름이_중복되는_경우() {
        List<Player> duplicatePlayerList = Arrays.asList(new Player("pobi"),
                new Player("pobi"),
                new Player("jay"));

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Players players = new Players(duplicatePlayerList);
        }).withMessage("이름은 중복될 수 없습니다");
    }
}