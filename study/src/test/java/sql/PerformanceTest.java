package sql;

import nextstep.jdbc.JdbcTemplate;
import nextstep.jdbc.ResultSetMappingStrategy;
import org.junit.jupiter.api.Test;
import sql.dto.HobbyResponse;
import sql.dto.YearsCodingResponse;
import sql.support.DummyData;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PerformanceTest {
    private JdbcTemplate jdbcTemplate = JdbcTemplate.builder().url("jdbc:mysql://localhost:13306/jwp_jdbc?useUnicode=true&characterEncoding=utf8")
            .driver("com.mysql.cj.jdbc.Driver")
            .userName("techcourse")
            .password("password")
            .build();

    @Test
    void ratio_구하기() {
        // given
        final String sql = "select hobby, count(*) / (select count(*) from survey_results_public) as ratio " +
                "from survey_results_public " +
                "group by hobby;";
        HobbyResponse expectedNo = new HobbyResponse("No", "0.1918");
        HobbyResponse expectedYes = new HobbyResponse("Yes", "0.8082");

        // when
        List<Object> results = jdbcTemplate.executeQuery(sql, (ResultSetMappingStrategy<Object>) resultSet -> {
            String cnt = resultSet.getString("hobby");
            String ratio = resultSet.getString("ratio");
            return new HobbyResponse(cnt, ratio);
        }).orElseThrow(RuntimeException::new);

        // assertTimeout 검증을 안해도된다고 바뀌어서 그냥 값만 뽑았습니다
        // then
        assertThat(results.get(0)).isEqualTo(expectedNo);
        assertThat(results.get(1)).isEqualTo(expectedYes);
    }

    @Test
    void years_coding_구하기() {
        // given
        final String sql = "SELECT dev_type AS 'type', ROUND(AVG(period), 1) AS 'mean' FROM dev_type group by dev_type;";

        // when
        List<Object> results = jdbcTemplate.executeQuery(sql, (ResultSetMappingStrategy<Object>) resultSet -> {
            String type = resultSet.getString("type");
            String mean = resultSet.getString("mean");
            return new YearsCodingResponse(type, mean);
        }).orElseThrow(RuntimeException::new);

        // then
        assertTrue(results.containsAll(DummyData.YEARS_CODING_RESPONSES));
    }
}
