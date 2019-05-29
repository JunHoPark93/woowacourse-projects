package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.util.InputUtil;

import java.util.*;
import java.util.stream.Collectors;

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

    public static List<Lotto> createManualLotto(ManualNumber manualNumber, final Scanner scanner) {
        List<Lotto> manualLottoList = new ArrayList<>();
        for (int i = 0; i < manualNumber.getNum(); i++) {
            String input = scanner.nextLine();
            InputUtil.checkLottoInput(input);
            addLotto(manualLottoList, input);
        }
        return manualLottoList;
    }

    private static void addLotto(List<Lotto> manualLottoList, String input) {
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(numbers);
        manualLottoList.add(new Lotto(lottoGenerator));
    }

    public static LottoResult getLottoResult(LottoBuyList lottoBuyList, WinningLotto winningLotto) {
        return winningLotto.getResult(lottoBuyList);
    }

    public static LottoBuyList joinBuyList(LottoBuyList manualBuyList, LottoBuyList autoBuyList) {
        return manualBuyList.joinBuyList(autoBuyList);
    }
}
