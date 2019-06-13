package com.woowacourse.lotto.service;

import com.woowacourse.lotto.db.dao.DrawDao;
import com.woowacourse.lotto.domain.BonusNumber;
import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.LottoResult;

import java.sql.SQLException;

public class DrawWebService extends DrawService {
    private int round;

    public DrawWebService(String lottoInput, String bonusNumberInput, LottoBuyList totalBuys, int round) {
        super(lottoInput, bonusNumberInput, totalBuys);
        this.round = round;
    }

    @Override
    public LottoResult createResult() {
        LottoResult lottoResult = super.createResult();
        addWinningLotto(lastWeekLotto, bonusNumber);
        return lottoResult;
    }

    private void addWinningLotto(Lotto lastWeekLotto, BonusNumber bonusNumber) {
        try {
            DrawDao.addWinningLotto(lastWeekLotto, bonusNumber, round);
        } catch (SQLException e) {
            System.out.println("결과 삽입 에러");
            System.exit(-1);
        }
    }
}
