package com.woowacourse.laddergame.domain;

import com.woowacourse.laddergame.util.NaturalNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LineTest {
    private int countOfPerson;
    private Line line;

    @BeforeEach
    void setUp() {
        countOfPerson = 4;
        line = new Line(new NaturalNumber(countOfPerson));
    }

    @Test
    void 라인_사람수만큼_포지션_초기화() {
        assertThat(line.getPositionCount()).isEqualTo(4);
    }

    @Test
    void 정상적_사다리_초기화() {
        //    |-----|     |-----|
        line.putBridge(new NaturalNumber(1));
        line.putBridge(new NaturalNumber(3));

        assertThat(line.isBridgeExist(1)).isTrue();
        assertThat(line.isBridgeExist(3)).isTrue();
    }

    @Test
    void 비정상적_사다리_초기화() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                line.putBridge(new NaturalNumber(4))).withMessage("다리를 놓을 수 없습니다");
    }

    @Test
    void 비정상적_사다리_초기화_인덱스_음수() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
                line.putBridge(new NaturalNumber(-1))).withMessage("자연수가 아닙니다.");
    }


    @Test
    void 사다리_그려지는_테스트_양쪽선() {
        String ladderShape = "     |-----|     |-----|";

        line.putBridge(new NaturalNumber(1));
        line.putBridge(new NaturalNumber(3));

        assertThat(line.toString()).isEqualTo(ladderShape);

    }

    @Test
    void 사다리_그려지는_테스트_중간만_선() {
        String ladderShape = "     |     |-----|     |";

        line.putBridge(new NaturalNumber(2));

        assertThat(line.toString()).isEqualTo(ladderShape);
    }

    @Test
    void 이미다리가_놓여져있을_때_예외처리() {
        line.putBridge(new NaturalNumber(2));

        assertThrows(IllegalArgumentException.class, () -> {
            line.putBridge(new NaturalNumber(2));
        });
    }

    @Test
    void 연속해서_다리를_놓는_예외처리() {
        line.putBridge(new NaturalNumber(2));

        assertThrows(IllegalArgumentException.class, () -> {
            line.putBridge(new NaturalNumber(3));
        });
    }

    @Test
    void 범위가_넘어가는_위치에_사다리_놓는경우_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            line.putBridge(new NaturalNumber(5));
        });
    }

    @Test
    void 동일한_라인인지_테스트() {
        assertThat(line).isEqualTo(new Line(new NaturalNumber(4)));
    }

    @Test
    void 라인_이동_테스트() {
        line.putBridge(new NaturalNumber(1));
        line.putBridge(new NaturalNumber(3));

        assertThat(line.takeLine(new NaturalNumber(1))).isEqualTo(2);
    }
}
