package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.service.ConsoleLottoService;
import com.woowacourse.lotto.service.LottoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputViewConsole  {
    private static LottoService lottoService = new ConsoleLottoService();

    public static LottoNumber inputBonusNumber(Lotto lastWeekLotto) {
        try {
            System.out.println("보너스 볼을 입력해 주세요.");
            return lottoService.createBonusNumber(Input.inputString(), lastWeekLotto);
        } catch (IllegalArgumentException e) {
            return inputBonusNumber(lastWeekLotto);
        }
    }

    public static Lotto inputLastWeekLotto() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            return lottoService.createLotto(Input.inputString());
        } catch (IllegalArgumentException e) {
            return inputLastWeekLotto();
        }
    }

    public static LottoBuyList inputManualBuys(ManualNumber manualNumber) {
        if (manualNumber.isEmpty()) {
            return new LottoBuyList(Collections.emptyList());
        }
        List<Lotto> manualLottoList = new ArrayList<>();
        try {
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
            for (int i = 0; i < manualNumber.getNum(); i++) {
                lottoService.addManualLotto(manualLottoList, Input.inputString());
                return new LottoBuyList(manualLottoList);
            }
        } catch (IllegalArgumentException e) {
            return inputManualBuys(manualNumber);
        }
        throw new RuntimeException("수동 구매 에러");
    }

    public static ManualNumber inputManualNumber(PurchaseMoney purchaseMoney) {
        try {
            System.out.println(OutputViewConsole.inputManualNumberMsg());
            return lottoService.createManualNumber(Input.inputString(), purchaseMoney);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 입력");
            return inputManualNumber(purchaseMoney);
        }
    }

    public static PurchaseMoney inputPurchaseMoney() {
        try {
            System.out.println(OutputViewConsole.inputPurchaseMoneyMsg());
            return lottoService.createPurchaseMoney(Input.inputString());
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 입력");
            return inputPurchaseMoney();
        }
    }
}
