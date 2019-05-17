package com.woowacourse.laddergame.domain.vo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ResultNameDtoTest {
    @Test
    void 이름_정상_입력() {
        ResultNameDto resultNameDto = new ResultNameDto();
        assertThatCode(() -> resultNameDto.setName("pobi")).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi ", " pobi", "po bi", "1", "pobi,"})
    void 이름_비정상_입력(String input) {
        ResultNameDto resultNameDto = new ResultNameDto();
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            resultNameDto.setName(input);
        }).withMessage("Result Name 이 잘못되었습니다");
    }

    @Test
    void 이름_null_입력() {
        ResultNameDto resultNameDto = new ResultNameDto();
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            resultNameDto.setName(null);
        }).withMessage("Null 은 입력할 수 없습니다");
    }
}