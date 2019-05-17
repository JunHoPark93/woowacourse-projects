package com.woowacourse.laddergame.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PlayerTest {
    @Test
    void 정상이름_초기화() {
        String name = "aiden";

        Player player = new Player(name);
        assertThat(player.getName()).isEqualTo("aiden");
    }

    @Test
    void 비정상이름_5자이상() {
        String name = "aidenjay";

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Player player = new Player(name);
        }).withMessage("이름은 5글자 까지 가능합니다");
    }

    @Test
    void 공백_입력() {
        String name = " ";

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Player player = new Player(name);
        }).withMessage("이름에 공백이 있으면 안됩니다");
    }

    @Test
    void null_입력() {
        String name = null;

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Player player = new Player(name);
        }).withMessage("null을 입력할 수 없습니다");
    }

    @Test
    void 공백이_있는_경우() {
        String name = "j ay";

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Player player = new Player(name);
        }).withMessage("이름에 공백이 있으면 안됩니다");
    }

    @Test
    void all_이라는_이름입력() {
        String name = "all";

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Player player = new Player(name);
        }).withMessage("all은 player 이름으로 입력할 수 없습니다");
    }
}
