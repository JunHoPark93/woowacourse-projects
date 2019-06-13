package com.woowacourse.lotto.db.dao;

import com.woowacourse.lotto.db.ConnectionFactory;
import com.woowacourse.lotto.domain.BonusNumber;
import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.util.LottoParser;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DrawDao {
    public static void addWinningLotto(Lotto winningLotto, BonusNumber bonusNumber, int round) throws SQLException {
        String query = "INSERT INTO W_LOTTO(round_id, winning_num, bonus_num) VALUES (?, ?, ?)";
        PreparedStatement pstmt = ConnectionFactory.getConnection().prepareStatement(query);
        pstmt.setString(1, String.valueOf(round));
        pstmt.setString(2, String.valueOf(LottoParser.removeBraces(winningLotto)));
        pstmt.setString(3, String.valueOf(bonusNumber.value()));

        pstmt.executeUpdate();
    }
}
