package nextstep.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    private final String driver;
    private final String url;
    private final String userName;
    private final String password;

    private JdbcTemplate(Builder builder) {
        driver = builder.driver;
        url = builder.url;
        userName = builder.userName;
        password = builder.password;
    }

    public static Builder builder() {
        return new Builder();
    }

    private Connection getConnection() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public void execute(String sql, String... args) {
        try (Connection conn = getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {
            setParams(psmt, args);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    private void setParams(PreparedStatement psmt, String... args) {
        for (int i = 0; i < args.length; i++) {
            try {
                psmt.setString(i + 1, args[i]);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IllegalArgumentException();
            }
        }
    }

    public <T> T select(String sql, ResultSetMappingStrategy<T> strategy, String... args) {
        try (Connection conn = getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {
            setParams(psmt, args);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return strategy.map(rs);
            }
            throw new IllegalArgumentException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public <T> List<T> selectAll(String sql, ResultSetMappingStrategy<T> strategy, String... args) {
        try (Connection conn = getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {
            setParams(psmt, args);
            ResultSet rs = psmt.executeQuery();
            List<T> result = new ArrayList<>();
            while (rs.next()) {
                result.add(strategy.map(rs));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
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
