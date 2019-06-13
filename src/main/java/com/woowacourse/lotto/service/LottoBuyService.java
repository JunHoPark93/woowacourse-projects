package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.ManualNumber;
import com.woowacourse.lotto.domain.PurchaseMoney;
import com.woowacourse.lotto.util.IntendedLottoGenerator;
import com.woowacourse.lotto.util.LottoGenerator;
import com.woowacourse.lotto.util.RandomLottoGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class LottoBuyService {
    private ManualNumber manualNumber;
    private PurchaseMoney purchaseMoney;
    private LottoBuyList manualBuyList;

    public LottoBuyService(ManualNumber manualNumber, PurchaseMoney purchaseMoney, LottoBuyList manualBuyList) {
        this.manualNumber = manualNumber;
        this.purchaseMoney = purchaseMoney;
        this.manualBuyList = manualBuyList;
    }

    public LottoBuyService(String moneyInput, String manualNumberInput, String[] manualLottos) {
        PurchaseMoney purchaseMoney = new PurchaseMoney(moneyInput);
        ManualNumber manualNumber = new ManualNumber(manualNumberInput, purchaseMoney);
        List<Lotto> manualLottoList = new ArrayList<>();
        manualLottos = Optional.ofNullable(manualLottos).orElse(new String[0]);

        for (String manualLotto : manualLottos) {
            addManualLotto(manualLottoList, manualLotto);
        }

        this.manualNumber = manualNumber;
        this.purchaseMoney = purchaseMoney;
        this.manualBuyList = new LottoBuyList(manualLottoList);
    }

    public LottoBuyList createTotalBuyList() {
        LottoBuyList autoBuys = getAutoLottoBuyList();
        return autoBuys.joinBuyList(manualBuyList);
    }

    public LottoBuyList getAutoLottoBuyList() {
        if (!purchaseMoney.isEnoughMoney(manualNumber)) {
            return new LottoBuyList(Collections.emptyList());
        }

        List<Lotto> lottoBuyList = createAutoLottoList();

        return new LottoBuyList(lottoBuyList);
    }

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

    private void addManualLotto(List<Lotto> manualLottoList, String input) {
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(numbers);
        manualLottoList.add(new Lotto(lottoGenerator));
    }
}
