package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.*;

import java.util.ArrayList;
import java.util.List;

public class LottoService {
    public static LottoBuyList getAutoLottoBuyList(PurchaseMoney purchaseMoney) {
        LottoGenerator lottoGenerator = new RandomLottoGenerator();
        int loop = purchaseMoney.getAvailableLottoSize();

        List<Lotto> lottoBuyList = new ArrayList<>();

        for (int i = 0; i < loop; i++) {
            lottoBuyList.add(new Lotto(lottoGenerator));
        }

        return new LottoBuyList(lottoBuyList);
    }

    public static LottoResult getLottoResult(LottoBuyList lottoBuyList, WinningLotto winningLotto) {
        return winningLotto.getResult(lottoBuyList);
    }

    public static LottoBuyList joinBuyList(LottoBuyList manualBuyList, LottoBuyList autoBuyList) {
        return manualBuyList.joinBuyList(autoBuyList);
    }
}
