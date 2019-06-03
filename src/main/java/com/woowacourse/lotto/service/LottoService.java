package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.ManualNumber;
import com.woowacourse.lotto.domain.PurchaseMoney;
import com.woowacourse.lotto.util.IntendedLottoGenerator;
import com.woowacourse.lotto.util.LottoGenerator;
import com.woowacourse.lotto.util.RandomLottoGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoService {
    public static LottoBuyList getAutoLottoBuyList(PurchaseMoney purchaseMoney, ManualNumber manualNumber) {
        if (!purchaseMoney.isEnoughMoney(manualNumber)) {
            return new LottoBuyList(Collections.emptyList());
        }

        List<Lotto> lottoBuyList = createAutoLottoList(purchaseMoney, manualNumber);

        return new LottoBuyList(lottoBuyList);
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

    public static void addManualLotto(List<Lotto> manualLottoList, String input) {
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(numbers);
        manualLottoList.add(new Lotto(lottoGenerator));
    }

    public static LottoBuyList joinBuyList(LottoBuyList manualBuyList, LottoBuyList autoBuyList) {
        return manualBuyList.joinBuyList(autoBuyList);
    }
}
