package com.woowacourse.laddergame.domain.vo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LadderDtoTest {
    @Test
    void Player_정상이름입력() {
        String input = "pobi,crong,honux";

        LadderDto ladderDto = new LadderDto();
        ladderDto.setNames(input);

        assertThat(ladderDto.getNames()).isEqualTo(input);
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobiiii,crong,honux", "po bi,crong", "  ", ",pobi", "pobi,", "pobi, jay"})
    void Player_비정상이름입력(String input) {
        LadderDto ladderDto = new LadderDto();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            ladderDto.setNames(input);
        }).withMessage("Player 이름들이 잘못되었습니다");
    }

    @Test
    void Player_null입력() {
        String input = null;

        LadderDto ladderDto = new LadderDto();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            ladderDto.setNames(input);
        }).withMessage("Null 은 입력할 수 없습니다");
    }

    @ParameterizedTest
    @ValueSource(strings = {"01", "ab", "!!", "-1", " ", "1_000"})
    void 높이_비정상값(String input) {
        LadderDto ladderDto = new LadderDto();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            ladderDto.setHeight(input);
        }).withMessage("정상적인 사다리 높이가 아닙니다");
    }

    @Test
    void 높이_null() {
        LadderDto ladderDto = new LadderDto();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            ladderDto.setHeight(null);
        }).withMessage("Null 은 입력할 수 없습니다");
    }

    @Test
    void 사다리_결과_정상_입력() {
        LadderDto ladderDto = new LadderDto();

        ladderDto.setNames("pobi,crong,jay,aiden,jm");
        ladderDto.setResult("꽝,10,아이스크림,꽝,꽝");
    }

    @Test
    void 사다리_결과_비정상_입력() {
        LadderDto ladderDto = new LadderDto();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            ladderDto.setResult("꽝,아이스크림,아이스크림,꽝,꽝");
        }).withMessage("이름이 먼저 초기화되야 합니다");
    }

    @Test
    void 사다리_결과_null_입력() {
        LadderDto ladderDto = new LadderDto();

        ladderDto.setNames("pobi,crong,jay,aiden,jm");
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            ladderDto.setResult(null);
        }).withMessage("Null 은 입력할 수 없습니다");
    }
}