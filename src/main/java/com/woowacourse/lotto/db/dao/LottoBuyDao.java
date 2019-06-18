package com.woowacourse.lotto.db.dao;

import com.woowacourse.lotto.db.ConnectionFactory;
import com.woowacourse.lotto.domain.LottoBuyList;
import com.woowacourse.lotto.util.LottoParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LottoBuyDao {
    public static void addBuys(LottoBuyList totalBuys, int round) throws SQLException {
        String query = "INSERT INTO BUY_HISTORY(user_id, round_id, lotto) VALUES (?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);

        for (int i = 0; i < totalBuys.size(); i++) {
            try {
                pstmt.setString(1, "1");
                pstmt.setString(2, String.valueOf(round));
                pstmt.setString(3, LottoParser.removeBraces(totalBuys.getLotto(i)));
                pstmt.addBatch();
                pstmt.clearParameters();
            } catch (Exception e) {
                System.err.println("LottoBuyDao rollback!" + e.getMessage());
                con.rollback();
            }
        }
        pstmt.executeBatch();
    }
}
