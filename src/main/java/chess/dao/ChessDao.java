package chess.dao;

import chess.db.ConnectionFactory;
import chess.domain.board.Square;
import chess.domain.dto.HistoryDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessDao {
    public static int getRound() throws SQLException {
        String query = "SELECT MAX(id) FROM ROUND;";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet resultSet = pstmt.executeQuery();

        if (!resultSet.next()) {
            throw new RuntimeException("no nextRound");
        }

        String lastRound = resultSet.getString("max(id)");
        return Integer.valueOf(lastRound);
    }

    public static void insertRound(int round, Square source, Square target) throws SQLException {
        String query = "INSERT INTO HISTORY(round_id, source, target) VALUES (?, ?, ?)";
        Connection con = ConnectionFactory.getConnection();

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, round);
            pstmt.setString(2, source.toString());
            pstmt.setString(3, target.toString());
            pstmt.execute();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void endRound(int round) throws SQLException {
        String query = "UPDATE ROUND SET end = 'Y' WHERE id = ?";
        Connection con = ConnectionFactory.getConnection();
        insertRound(round, query, con);
    }

    public static void addRound(int round) throws SQLException {
        String query = "INSERT INTO ROUND(id) VALUES (?)";
        Connection con = ConnectionFactory.getConnection();
        insertRound(round, query, con);
    }

    private static void insertRound(int round, String query, Connection con) {
        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, round);
            pstmt.execute();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static boolean notEnd() throws SQLException {
        String query = "SELECT * FROM ROUND WHERE id = (SELECT MAX(id) FROM ROUND);";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            String end = rs.getString("end");
            return end.equals("N");
        }

        throw new SQLException("데이터가 없습니다");
    }

    public static List<HistoryDto> getHistory(int round) throws SQLException {
        List<HistoryDto> historyDtos = new ArrayList<>();

        String query = "SELECT source, target FROM HISTORY WHERE round_id = ?;";
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, round);
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            String source = rs.getString("source");
            String target = rs.getString("target");
            historyDtos.add(new HistoryDto(source, target));
        }
        return historyDtos;
    }
}
