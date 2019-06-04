package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.service.LottoService;
import com.woowacourse.lotto.util.InputUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class InputViewConsole implements InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public PurchaseMoney getMoneyFromUser() {
        try {
            System.out.println("구입금액을 입력해 주세요.");
            String input = SCANNER.nextLine();

            return new PurchaseMoney(Integer.parseInt(input));
        } catch (IllegalArgumentException e) {
            System.out.println("입력이 잘못 되었습니다");
            return getMoneyFromUser();
        }
    }

    @Override
    public ManualNumber getManualNumberFromUser(PurchaseMoney purchaseMoney) {
        try {
            System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
            String input = SCANNER.nextLine();
            int lottoCount = Integer.parseInt(input);
            checkAvailableLottoNum(lottoCount * Lotto.PRICE, purchaseMoney);

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

    @Override
    public Lotto getLastWeekLottoFromUser() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            String input = SCANNER.nextLine();

            return InputUtil.parseStringToLotto(input);
        } catch (IllegalArgumentException e) {
            System.out.println("입력이 잘못 되었습니다");
            return getLastWeekLottoFromUser();
        }
    }

    @Override
    public LottoNumber getBonusNumberFromUser(Lotto lastWeekLotto) {
        try {
            System.out.println("보너스 볼을 입력해 주세요.");
            String input = SCANNER.nextLine();
            int bonusNum = Integer.parseInt(input);
            checkDuplicateBonusNum(lastWeekLotto, bonusNum);

            return new LottoNumber(bonusNum);
        } catch (IllegalArgumentException e) {
            System.out.println("입력이 잘못 되었습니다");
            return getBonusNumberFromUser(lastWeekLotto);
        }
    }

    private static void checkDuplicateBonusNum(Lotto lastWeekLotto, int bonusNum) {
        if (lastWeekLotto.contains(new LottoNumber(bonusNum))) {
            throw new IllegalArgumentException("보너스 번호와 로또번호가 중복입니다");
        }
    }

    @Override
    public LottoBuyList getManualLottoFromUser(ManualNumber manualNumber) {
        List<Lotto> manualLottoList = new ArrayList<>();
        if (manualNumber.getNum() == 0) {
            return new LottoBuyList(Collections.emptyList());
        }
        try {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
            getLottoNumber(manualNumber, manualLottoList);

            return new LottoBuyList(manualLottoList);
        } catch (IllegalArgumentException e) {
            System.out.println("입력이 잘못 되었습니다");
            return getManualLottoFromUser(manualNumber);
        }
    }

    private void getLottoNumber(ManualNumber manualNumber, List<Lotto> manualLottoList) {
        for (int i = 0; i < manualNumber.getNum(); i++) {
            String input = SCANNER.nextLine();
            LottoService.addManualLotto(manualLottoList, input);
        }
    }
}
