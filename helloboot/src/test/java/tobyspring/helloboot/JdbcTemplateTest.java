package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@HellobootTest
@Rollback(false)
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hello(name varchar(50) primary key, count int)");
    }

    @Test
    public void insertAndQuery() {
        jdbcTemplate.update("INSERT INTO hello VALUES (?, ?)", "Toby", 3);
        jdbcTemplate.update("INSERT INTO hello VALUES (?, ?)", "Spring", 1);

        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM hello", Long.class);
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void insertAndQuery2() {
        jdbcTemplate.update("INSERT INTO hello VALUES (?, ?)", "Toby", 3);
        jdbcTemplate.update("INSERT INTO hello VALUES (?, ?)", "Spring", 1);

        Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM hello", Long.class);
        assertThat(count).isEqualTo(2);
    }
}
