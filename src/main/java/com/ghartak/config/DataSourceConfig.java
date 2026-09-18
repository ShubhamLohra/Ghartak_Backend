package com.ghartak.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        String rawUrl = System.getenv("SPRING_DATASOURCE_URL");
        if (rawUrl == null || rawUrl.trim().isEmpty()) {
            rawUrl = System.getenv("DATABASE_URL");
        }
        if (rawUrl == null || rawUrl.trim().isEmpty()) {
            rawUrl = System.getenv("INTERNAL_DATABASE_URL");
        }

        HikariConfig config = new HikariConfig();

        if (rawUrl != null && !rawUrl.trim().isEmpty()) {
            String trimmedUrl = rawUrl.trim();

            // Convert postgres://user:pass@host:5432/dbname -> jdbc:postgresql://host:5432/dbname
            if (trimmedUrl.startsWith("postgres://") || trimmedUrl.startsWith("postgresql://")) {
                try {
                    String httpUrl = trimmedUrl.startsWith("postgres://") 
                            ? trimmedUrl.replace("postgres://", "http://") 
                            : trimmedUrl.replace("postgresql://", "http://");
                    URI uri = new URI(httpUrl);

                    String host = uri.getHost();
                    int port = uri.getPort() == -1 ? 5432 : uri.getPort();
                    String database = uri.getPath();

                    config.setJdbcUrl("jdbc:postgresql://" + host + ":" + port + database);
                    config.setDriverClassName("org.postgresql.Driver");

                    if (uri.getUserInfo() != null) {
                        String[] credentials = uri.getUserInfo().split(":", 2);
                        config.setUsername(credentials[0]);
                        if (credentials.length > 1) {
                            config.setPassword(credentials[1]);
                        }
                    }
                    config.setMaximumPoolSize(5);
                    System.out.println(">>> Configured PostgreSQL DataSource: " + config.getJdbcUrl() + " with user: " + config.getUsername());
                    return new HikariDataSource(config);
                } catch (Exception e) {
                    System.err.println("Error parsing postgres URL: " + e.getMessage());
                }
            }

            // Handle explicit JDBC URL format: jdbc:postgresql://...
            if (trimmedUrl.startsWith("jdbc:")) {
                config.setJdbcUrl(trimmedUrl);
                String driver = System.getenv("SPRING_DATASOURCE_DRIVER");
                config.setDriverClassName(driver != null ? driver : "org.postgresql.Driver");
                config.setUsername(System.getenv("SPRING_DATASOURCE_USERNAME"));
                config.setPassword(System.getenv("SPRING_DATASOURCE_PASSWORD"));
                config.setMaximumPoolSize(5);
                System.out.println(">>> Configured Explicit JDBC DataSource: " + config.getJdbcUrl());
                return new HikariDataSource(config);
            }
        }

        // Fallback to zero-config embedded H2
        config.setJdbcUrl("jdbc:h2:mem:ghartakdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        config.setDriverClassName("org.h2.Driver");
        config.setUsername("sa");
        config.setPassword("");
        config.setMaximumPoolSize(5);
        System.out.println(">>> Configured Embedded H2 DataSource");
        return new HikariDataSource(config);
    }
}
