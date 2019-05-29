package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.util.InputUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final int LOTTO_PRICE = 1000;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static PurchaseMoney getMoneyFromUser() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            String input = SCANNER.nextLine();
            InputUtil.checkPurchaseMoneyInput(input);

            return new PurchaseMoney(Integer.parseInt(input));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getMoneyFromUser();
        }
    }

    public static ManualNumber getManualNumberFromUser(PurchaseMoney purchaseMoney) {
        try {
            System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
            String input = SCANNER.nextLine();
            InputUtil.checkManualNumber(input);
            int lottoCount = Integer.parseInt(input);
            checkAvailableLottoNum(lottoCount * LOTTO_PRICE, purchaseMoney);

            return new ManualNumber(lottoCount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getManualNumberFromUser(purchaseMoney);
        }
    }

    private static void checkAvailableLottoNum(int money, PurchaseMoney purchaseMoney) {
        if (!purchaseMoney.isAcceptableMoney(money)) {
            throw new IllegalArgumentException("구매할 돈이 부족합니다");
        }
    }

    public static Lotto getLastWeekLottoFromUser() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            String input = SCANNER.nextLine();
            InputUtil.checkLottoInput(input);

            return InputUtil.parseStringToLotto(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getLastWeekLottoFromUser();
        }
    }

    public static LottoNumber getBonusNumberFromUser(Lotto lastWeekLotto) {
        try {
            System.out.println("보너스 볼을 입력해 주세요.");
            String input = SCANNER.nextLine();
            InputUtil.checkBonusBallInput(input);
            int bonusNum = Integer.parseInt(input);
            checkDuplicateBonusNum(lastWeekLotto, bonusNum);

            return new LottoNumber(bonusNum);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBonusNumberFromUser(lastWeekLotto);
        }
    }

    private static void checkDuplicateBonusNum(Lotto lastWeekLotto, int bonusNum) {
        if (lastWeekLotto.contains(new LottoNumber(bonusNum))) {
            throw new IllegalArgumentException("보너스 번호와 로또번호가 중복입니다");
        }
    }

    public static LottoBuyList getManualLottoFromUser(ManualNumber manualNumber) {
        try {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
            List<Lotto> manualLottoList = new ArrayList<>();
            for (int i = 0; i < manualNumber.getNum(); i++) {
                String input = SCANNER.nextLine();
                InputUtil.checkLottoInput(input);
                List<Integer> numbers = Arrays.stream(input.split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
                LottoGenerator lottoGenerator = new IntendedLottoGenerator(numbers);
                manualLottoList.add(new Lotto(lottoGenerator));
            }
            return new LottoBuyList(manualLottoList);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getManualLottoFromUser(manualNumber);
        }
    }
}