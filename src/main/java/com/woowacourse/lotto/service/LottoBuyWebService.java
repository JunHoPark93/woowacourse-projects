package com.woowacourse.lotto.service;

import com.woowacourse.lotto.db.dao.LottoBuyDao;
import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.ManualNumber;
import com.woowacourse.lotto.domain.PurchaseMoney;
import com.woowacourse.lotto.util.IntendedLottoGenerator;
import com.woowacourse.lotto.util.LottoGenerator;
import com.woowacourse.lotto.util.RandomLottoGenerator;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class LottoBuyWebService implements LottoBuyService {
    private ManualNumber manualNumber;
    private PurchaseMoney purchaseMoney;
    private LottoBuyList manualBuyList;
    private int round;

    public LottoBuyWebService(String moneyInput, String manualNumberInput, String[] manualLottos, int round) {
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
        this.round = round;
    }

    @Override
    public LottoBuyList createTotalBuyList() {
        LottoBuyList totalBuys = getAutoLottoBuyList().joinBuyList(manualBuyList);
        addBuys(totalBuys);
        return totalBuys;
    }

    private void addBuys(LottoBuyList totalBuys) {
        try {
            LottoBuyDao.addBuys(totalBuys, round);
        } catch (SQLException e) {
            System.out.println("구매목록 삽입 에러");
            System.exit(-1);
        }
    }

    @Override
    public LottoBuyList getAutoLottoBuyList() {
        if (!purchaseMoney.isEnoughMoney(manualNumber)) {
            return new LottoBuyList(Collections.emptyList());
        }

        List<Lotto> lottoBuyList = createAutoLottoList();

        return new LottoBuyList(lottoBuyList);
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

    @Override
    public PurchaseMoney getPurchaseMoney() {
        return purchaseMoney;
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
