package com.woowacourse.laddergame.domain.vo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LadderVOTest {
    @Test
    void Player_정상이름입력() {
        String input = "pobi,crong,honux";

        LadderVO ladderVO = new LadderVO();
        ladderVO.setNames(input);

        assertThat(ladderVO.getNames()).isEqualTo(input);
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobiiii,crong,honux", "po bi,crong", "  ", ",pobi", "pobi,", "pobi, jay"})
    void Player_비정상이름입력(String input) {
        LadderVO ladderVO = new LadderVO();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            ladderVO.setNames(input);
        }).withMessage("Player 이름들이 잘못되었습니다");
    }

    @Test
    void Player_null입력() {
        String input = null;

        LadderVO ladderVO = new LadderVO();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            ladderVO.setNames(input);
        }).withMessage("Null 은 입력할 수 없습니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "ab", "!!", "-1", " ", "1_000"})
    void 높이_비정상값(String input) {
        LadderVO ladderVO = new LadderVO();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            ladderVO.setHeight(input);
        }).withMessage("정상적인 사다리 높이가 아닙니다");
    }

    @Test
    void 높이_null() {
        LadderVO ladderVO = new LadderVO();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            ladderVO.setHeight(null);
        }).withMessage("Null 은 입력할 수 없습니다");
    }
}