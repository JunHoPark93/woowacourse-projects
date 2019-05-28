package com.woowacourse.lotto.util;

import com.woowacourse.lotto.domain.IntendedLottoGenerator;
import com.woowacourse.lotto.domain.Lotto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class InputUtilTest {
    @Test
    void 구입금액_입력_검증() {
        assertThatCode(() -> InputUtil.checkPurchaseMoneyInput("14000")).doesNotThrowAnyException();
    }

    @Test
    void 구입금액_비정상_입력() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            InputUtil.checkPurchaseMoneyInput("hello");
        }).withMessage("구입 금액 입력이 잘못되었습니다");
    }

    @Test
    void 당첨번호_입력_검증() {
        assertThatCode(() -> InputUtil.checkWinningLottoInput("1, 2, 3, 4, 5, 6")).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(strings = {"d,f,2,3", "1,2,3,4,5", "2,3,5,6,7,f"})
    void 당첨번호_비정상_입력() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            InputUtil.checkWinningLottoInput("d,f,2,3");
        }).withMessage("당첨 번호 입력이 잘못 되었습니다");
    }

    @Test
    void 입력을_로또로_파싱() {
        String input = "1,2,3,4,5,6";
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        IntendedLottoGenerator intendedLottoGenerator = new IntendedLottoGenerator(list);

        Lotto lotto = new Lotto(intendedLottoGenerator);

        assertThat(InputUtil.parseStringToLotto(input)).isEqualTo(lotto);
    }
}
