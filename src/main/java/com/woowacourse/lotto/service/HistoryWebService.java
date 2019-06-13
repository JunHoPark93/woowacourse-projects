package com.woowacourse.lotto.service;

import com.woowacourse.lotto.db.dao.LottoDao;
import com.woowacourse.lotto.domain.History;

import java.sql.SQLException;

public class HistoryWebService {
    public History createHistory(String round) throws SQLException {
        return LottoDao.selectHistory(round);
    }
}
