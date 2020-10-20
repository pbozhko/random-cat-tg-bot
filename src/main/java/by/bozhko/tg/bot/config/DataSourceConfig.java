package by.bozhko.tg.bot.config;

import by.bozhko.tg.bot.config.properties.ApplicationProperties;
import by.bozhko.tg.bot.config.properties.DataSourceProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class DataSourceConfig {

    private final static String DRIVER_CLASS_NAME = "org.postgresql.Driver";

    private final ApplicationProperties applicationProperties;

    @Bean
    HikariDataSource hikariDataSource() {

        DataSourceProperties dataSourceProperties = applicationProperties.getDataSourceProperties();

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(DRIVER_CLASS_NAME);
        config.setJdbcUrl(dataSourceProperties.getUrl());
        config.setMaximumPoolSize(dataSourceProperties.getConnectionPoolSize());
        config.setConnectionTimeout(dataSourceProperties.getConnectionTimeout());

        HikariDataSource hikariDataSource = new HikariDataSource(config);

        Flyway.configure()
            .dataSource(hikariDataSource)
            .load()
            .migrate();

        return hikariDataSource;
    }
}
