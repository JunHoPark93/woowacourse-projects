package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.ManualNumber;
import com.woowacourse.lotto.domain.PurchaseMoney;
import com.woowacourse.lotto.util.LottoGenerator;
import com.woowacourse.lotto.util.RandomLottoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoBuyConsoleService implements LottoBuyService {
    private ManualNumber manualNumber;
    private PurchaseMoney purchaseMoney;
    private LottoBuyList manualBuyList;

    public LottoBuyConsoleService(ManualNumber manualNumber, PurchaseMoney purchaseMoney, LottoBuyList manualBuyList) {
        this.manualNumber = manualNumber;
        this.purchaseMoney = purchaseMoney;
        this.manualBuyList = manualBuyList;
    }

    @Override
    public LottoBuyList createTotalBuyList() {
        LottoBuyList autoBuys = getAutoLottoBuyList();
        return autoBuys.joinBuyList(manualBuyList);
    }

    @Override
    public LottoBuyList getAutoLottoBuyList() {
        if (!purchaseMoney.isEnoughMoney(manualNumber)) {
            return new LottoBuyList(Collections.emptyList());
        }

        List<Lotto> lottoBuyList = createAutoLottoList();

        return new LottoBuyList(lottoBuyList);
    }

    @Override
    public PurchaseMoney getPurchaseMoney() {
        return purchaseMoney;
    }

    private List<Lotto> createAutoLottoList() {
        List<Lotto> lottoBuyList = new ArrayList<>();
        LottoGenerator lottoGenerator = new RandomLottoGenerator();

        int loop = purchaseMoney.getAvailableLottoSize() - manualNumber.getNum();
        for (int i = 0; i < loop; i++) {
            lottoBuyList.add(new Lotto(lottoGenerator));
        }

        return lottoBuyList;
    }
}
