package ir.bigz.rediscache.global;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("ir.bigz.rediscache.domain.item")
@EnableTransactionManagement
public class JpaConfig {
}
