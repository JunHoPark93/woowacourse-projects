package com.woowacourse.lotto.service;

import com.woowacourse.lotto.db.dao.LottoDao;
import com.woowacourse.lotto.domain.BonusNumber;
import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.domain.LottoResult;

import java.sql.SQLException;

public class DrawWebService extends DrawService {
    public DrawWebService(String lottoInput, String bonusNumberInput, LottoBuyList totalBuys) {
        super(lottoInput, bonusNumberInput, totalBuys);
    }

    @Override
    public LottoResult createResult() {
        LottoResult lottoResult = super.createResult();
        addWinningLotto(lastWeekLotto, bonusNumber);
        return lottoResult;
    }

    private void addWinningLotto(Lotto lastWeekLotto, BonusNumber bonusNumber) {
        try {
            LottoDao.addWinningLotto(lastWeekLotto, bonusNumber);
        } catch (SQLException e) {
            System.out.println("결과 삽입 에러");
            System.exit(-1);
        }
    }
}
