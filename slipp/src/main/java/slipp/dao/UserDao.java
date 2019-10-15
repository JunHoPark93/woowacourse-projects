package slipp.dao;

import nextstep.jdbc.JdbcTemplate;
import slipp.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDao {
    private static final String INSERT_QUERY = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE USERS SET password = ?, name = ?, email = ? where userId = ?";
    private static final String SELECT_ALL_QUERY = "SELECT userId, password, name, email FROM USERS";
    private static final String SELECT_QUERY = "SELECT userId, password, name, email FROM USERS WHERE userid=?";

    private JdbcTemplate jdbcTemplate = JdbcTemplate.builder()
            .driver("org.h2.Driver")
            .url("jdbc:h2:mem:jwp-framework")
            .userName("sa")
            .password("")
            .build();

    public void insert(User user) {
        jdbcTemplate.execute(INSERT_QUERY, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
        jdbcTemplate.execute(UPDATE_QUERY, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
        List<Map<String, String>> result = jdbcTemplate.selectAll(SELECT_ALL_QUERY);
        List<User> users = new ArrayList<>();
        for (Map<String, String> map : result) {
            users.add(createUser(map));
        }
        return users;
    }

    public User findByUserId(String userId) {
        Map<String, String> result = jdbcTemplate.select(SELECT_QUERY, userId);
        return createUser(result);
    }

    private User createUser(Map<String, String> result) {
        return new User(result.get("USERID"), result.get("PASSWORD"), result.get("NAME"), result.get("EMAIL"));
    }
}
