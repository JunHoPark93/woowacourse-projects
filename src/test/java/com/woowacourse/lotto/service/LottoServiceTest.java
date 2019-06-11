package com.woowacourse.lotto.service;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.util.IntendedLottoGenerator;
import com.woowacourse.lotto.util.LottoGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoServiceTest {
    private LottoService lottoService;

    @BeforeEach
    void setUp() {
        lottoService = new ConsoleLottoService();
    }

    @Test
    void 구매금액_검증() {
        PurchaseMoney purchaseMoney = new PurchaseMoney(1000);

        PurchaseMoney actualPurchaseMoney = lottoService.createPurchaseMoney("1000");

        assertThat(purchaseMoney).isEqualTo(actualPurchaseMoney);
    }

    @Test
    void 수동구매숫자_검증() {
        PurchaseMoney purchaseMoney = new PurchaseMoney(3000);
        ManualNumber manualNumber = new ManualNumber(3);

        ManualNumber actualNumber = lottoService.createManualNumber("3", purchaseMoney);

        assertThat(manualNumber).isEqualTo(actualNumber);
    }

    @Test
    void 수동로또_추가() {
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Lotto> manualLottoList = new ArrayList<>();
        manualLottoList.add(new Lotto(lottoGenerator));

        lottoService.addManualLotto(manualLottoList, "5, 6, 7, 8, 9, 10");

        assertThat(manualLottoList).hasSize(2);
    }

    @Test
    void 로또_생성() {
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto expectedLotto = new Lotto(lottoGenerator);

        Lotto actualLotto = lottoService.createLotto("1, 2, 3, 4, 5, 6");

        assertThat(expectedLotto).isEqualTo(actualLotto);

    }

    @Test
    void 보너스번호_생성() {
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lastWeekLotto = new Lotto(lottoGenerator);

        LottoNumber bonusNum = lottoService.createBonusNumber("10", lastWeekLotto);

        assertThat(bonusNum.getLottoNum()).isEqualTo(10);
    }

    @Test
    void 전체_구매리스트() {
        PurchaseMoney purchaseMoney = new PurchaseMoney(3000);
        ManualNumber manualNumber = new ManualNumber(1);
        LottoGenerator lottoGenerator = new IntendedLottoGenerator(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto lotto = new Lotto(lottoGenerator);
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(lotto);
        LottoBuyList lottoBuyList = new LottoBuyList(lottos);

        LottoBuyList totalBuyList = lottoService.createTotalBuyList(lottoBuyList, purchaseMoney, manualNumber);

        assertThat(totalBuyList.getLottoSize()).isEqualTo(3);
    }
}