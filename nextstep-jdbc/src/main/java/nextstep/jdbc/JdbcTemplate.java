package nextstep.jdbc;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public void insert(String sql, String... args) {
        try (Connection conn = getConnection();
             PreparedStatement psmt = conn.prepareStatement(sql)) {
            setParams(psmt, args);
            psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setParams(PreparedStatement psmt, String... args) {
        for (int i = 0; i < args.length; i++) {
            try {
                psmt.setString(i + 1, args[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static final class Builder {
        private String driver;
        private String url;
        private String userName;
        private String password;

        public Builder() {
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
