package nextstep.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    private final ConnectionManager connectionManager;

    private JdbcTemplate(Builder builder) {
        connectionManager = new ConnectionManager(builder.driver, builder.url, builder.userName, builder.password);
    }

    public static Builder builder() {
        return new Builder();
    }

    public void execute(String sql, String... args) {
        try (PreparedStatement psmt = prepare(sql)) {
            setParams(psmt, args);
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    private PreparedStatement prepare(String sql) throws SQLException {
        return this.connectionManager.getConnection().prepareStatement(sql);
    }

    private void setParams(PreparedStatement psmt, String... args) {
        for (int i = 0; i < args.length; i++) {
            try {
                psmt.setString(i + 1, args[i]);
            } catch (SQLException e) {
                throw new IllegalArgumentException();
            }
        }
    }

    public <T> T select(String sql, ResultSetMappingStrategy<T> strategy, String... args) {
        try (PreparedStatement psmt = prepare(sql)) {
            setParams(psmt, args);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return strategy.map(rs);
            }
            throw new IllegalArgumentException();
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public <T> List<T> selectAll(String sql, ResultSetMappingStrategy<T> strategy, String... args) {
        try (PreparedStatement psmt = prepare(sql)) {
            setParams(psmt, args);
            ResultSet rs = psmt.executeQuery();
            return mapResultSet(strategy, rs);
        } catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    private <T> List<T> mapResultSet(ResultSetMappingStrategy<T> strategy, ResultSet rs) throws SQLException {
        List<T> result = new ArrayList<>();
        while (rs.next()) {
            result.add(strategy.map(rs));
        }
        return result;
    }

    public static final class Builder {
        private String driver;
        private String url;
        private String userName;
        private String password;

        private Builder() {
        }

        public Builder driver(String driver) {
            this.driver = driver;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public JdbcTemplate build() {
            return new JdbcTemplate(this);
        }
    }
}
