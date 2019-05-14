package com.woowacourse.laddergame.domain;

import com.woowacourse.laddergame.util.NaturalNumber;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LineTest {
    @Test
    void 라인_사람수만큼_포지션_초기화() {
        int countOfPerson = 4;

        Line line = new Line(new NaturalNumber(countOfPerson));

        assertThat(line.getPositionCount()).isEqualTo(4);
    }

    @Test
    void 정상적_사다리_초기화() {
        int countOfPerson = 4;

        Line line = new Line(new NaturalNumber(countOfPerson));

        //    |-----|     |-----|
        line.putBridge(new NaturalNumber(1));
        line.putBridge(new NaturalNumber(3));

        assertThat(line.isBridgeExist(1)).isTrue();
        assertThat(line.isBridgeExist(3)).isTrue();
    }

    @Test
    void 비정상적_사다리_초기화_() {
        int countOfPerson = 4;

        Line line = new Line(new NaturalNumber(countOfPerson));

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                line.putBridge(new NaturalNumber(4))).withMessage("다리를 놓을 수 없습니다");
    }

    @Test
    void 비정상적_사다리_초기화_인덱스_음수() {
        int countOfPerson = 4;

        Line line = new Line(new NaturalNumber(countOfPerson));

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                line.putBridge(new NaturalNumber(-1))).withMessage("자연수가 아닙니다.");
    }
}
