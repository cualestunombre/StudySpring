package hello.config;

import hello.AutoConfigApplication;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionManager;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.sql.DataSource;

@SpringBootTest
public class DbConfigTest {
    @Autowired
    DataSource dataSource;
    @Autowired
    TransactionManager transactionManager;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void checkBean(){
        assertThat(dataSource).isNotNull();
        assertThat(transactionManager).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
    }
}
