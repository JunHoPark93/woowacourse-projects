package nextstep.di.factory.example;

import nextstep.annotation.Bean;
import nextstep.annotation.Configuration;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

@Configuration
public class IntegrationConfig {
    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUrl("jdbc:h2:~/jwp-basic;AUTO_SERVER=TRUE");
        ds.setUsername("sa");
        ds.setPassword("");
        return ds;
    }

    @Bean
    public MyJdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new MyJdbcTemplate(dataSource);
    }
}
