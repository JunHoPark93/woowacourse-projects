package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.PurchaseMoney;

public interface LottoBuyService {
    LottoBuyList createTotalBuyList();
    LottoBuyList getAutoLottoBuyList();
    PurchaseMoney getPurchaseMoney();
}
