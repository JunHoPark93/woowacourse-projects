package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoService {
    private static final int LOTTO_PRICE = 1000;

    public static LottoBuyList getAutoLottoBuyList(PurchaseMoney purchaseMoney, ManualNumber manualNumber) {
        if (isNotEnoughMoney(purchaseMoney, manualNumber)) {
            return new LottoBuyList(Collections.emptyList());
        }

        List<Lotto> lottoBuyList = createAutoLottoList(purchaseMoney, manualNumber);

        return new LottoBuyList(lottoBuyList);
    }

    private static boolean isNotEnoughMoney(PurchaseMoney purchaseMoney, ManualNumber manualNumber) {
        return purchaseMoney.getMoney() - manualNumber.getTotalPrice() < LOTTO_PRICE;
    }

    private static List<Lotto> createAutoLottoList(PurchaseMoney purchaseMoney, ManualNumber manualNumber) {
        List<Lotto> lottoBuyList = new ArrayList<>();
        LottoGenerator lottoGenerator = new RandomLottoGenerator();

        int loop = purchaseMoney.getAvailableLottoSize() - manualNumber.getNum();
        for (int i = 0; i < loop; i++) {
            lottoBuyList.add(new Lotto(lottoGenerator));
        }

        return lottoBuyList;
    }

    public static LottoResult getLottoResult(LottoBuyList lottoBuyList, WinningLotto winningLotto) {
        return winningLotto.getResult(lottoBuyList);
    }

    public static LottoBuyList joinBuyList(LottoBuyList manualBuyList, LottoBuyList autoBuyList) {
        return manualBuyList.joinBuyList(autoBuyList);
    }
}
