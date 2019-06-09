package com.woowacourse.lotto.util;

import com.woowacourse.lotto.domain.LottoNumber;

import java.util.List;

public interface LottoGenerator {
    List<LottoNumber> generateNumber();
}
