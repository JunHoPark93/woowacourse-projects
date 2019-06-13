package com.woowacourse.lotto.util;

import com.woowacourse.lotto.domain.Lotto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoParserTest {
    @Test
    void 입력을_로또로_파싱() {
        String input = "1, 2, 3, 4, 5, 6";
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(list);

        Lotto lotto = new Lotto(lottoGenerator);

        assertThat(LottoParser.parseStringToLotto(input)).isEqualTo(lotto);
    }
}