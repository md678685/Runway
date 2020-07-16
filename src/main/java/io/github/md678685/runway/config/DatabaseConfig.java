package io.github.md678685.runway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "io.github.md678685.runway.db.repository")
public class DatabaseConfig {
}
