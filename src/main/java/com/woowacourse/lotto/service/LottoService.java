package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.util.IntendedLottoGenerator;
import com.woowacourse.lotto.util.LottoGenerator;
import com.woowacourse.lotto.util.RandomLottoGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class LottoService {
    public PurchaseMoney createPurchaseMoney(String input) {
        return new PurchaseMoney(Integer.parseInt(input));
    }

    public ManualNumber createManualNumber(String input, PurchaseMoney purchaseMoney) {
        int lottoCount = Integer.parseInt(input);
        checkAvailableLottoNum(lottoCount * Lotto.PRICE, purchaseMoney);

        return new ManualNumber(lottoCount);
    }

    private void checkAvailableLottoNum(int money, PurchaseMoney purchaseMoney) {
        if (!purchaseMoney.isAcceptableMoney(money)) {
            throw new IllegalArgumentException("구매할 돈이 부족합니다");
        }
    }

    private List<Lotto> createAutoLottoList(PurchaseMoney purchaseMoney, ManualNumber manualNumber) {
        List<Lotto> lottoBuyList = new ArrayList<>();
        LottoGenerator lottoGenerator = new RandomLottoGenerator();

        int loop = purchaseMoney.getAvailableLottoSize() - manualNumber.getNum();
        for (int i = 0; i < loop; i++) {
            lottoBuyList.add(new Lotto(lottoGenerator));
        }

        return lottoBuyList;
    }

    public void addManualLotto(List<Lotto> manualLottoList, String input) {
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(numbers);
        manualLottoList.add(new Lotto(lottoGenerator));
    }

    public static Lotto createLotto(String input) {
        String[] tokens = input.split(",");
        checkValidLotto(tokens);

        List<Integer> lottoNumbers = Arrays.stream(tokens)
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        IntendedLottoGenerator intendedLottoGenerator = new IntendedLottoGenerator(lottoNumbers);

        return new Lotto(intendedLottoGenerator);
    }

    private static void checkValidLotto(String[] tokens) {
        if (tokens.length != Lotto.COMPOSITE_NUM) {
            throw new IllegalArgumentException("로또 생성 에러");
        }
    }

    public static LottoNumber createBonusNumber(String input, Lotto lastWeekLotto) {
        int bonusNum = Integer.parseInt(input);
        checkDuplicateBonusNum(lastWeekLotto, bonusNum);

        return new LottoNumber(bonusNum);
    }

    private static void checkDuplicateBonusNum(Lotto lastWeekLotto, int bonusNum) {
        if (lastWeekLotto.contains(new LottoNumber(bonusNum))) {
            throw new IllegalArgumentException("보너스 번호와 로또번호가 중복입니다");
        }
    }

    protected LottoBuyList getAutoLottoBuyList(PurchaseMoney purchaseMoney, ManualNumber manualNumber) {
        if (!purchaseMoney.isEnoughMoney(manualNumber)) {
            return new LottoBuyList(Collections.emptyList());
        }

        List<Lotto> lottoBuyList = createAutoLottoList(purchaseMoney, manualNumber);

        return new LottoBuyList(lottoBuyList);
    }

    public abstract LottoResult createResult(LottoBuyList totalBuys, Lotto lastWeekLotto, LottoNumber bonusNumber);

    public abstract LottoBuyList createTotalBuyList(LottoBuyList manualBuys, PurchaseMoney purchaseMoney, ManualNumber manualNumber);
}
