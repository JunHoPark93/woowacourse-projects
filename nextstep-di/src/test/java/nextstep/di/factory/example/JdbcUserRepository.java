package nextstep.di.factory.example;

import nextstep.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {
    public JdbcUserRepository() {
    }
}
