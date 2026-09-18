package com.ghartak.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.URI;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    public DataSourceProperties dataSourceProperties() {
        DataSourceProperties properties = new DataSourceProperties();

        String rawUrl = System.getenv("SPRING_DATASOURCE_URL");
        if (rawUrl == null || rawUrl.trim().isEmpty()) {
            rawUrl = System.getenv("DATABASE_URL");
        }
        if (rawUrl == null || rawUrl.trim().isEmpty()) {
            rawUrl = System.getenv("INTERNAL_DATABASE_URL");
        }

        if (rawUrl != null && !rawUrl.trim().isEmpty()) {
            String trimmedUrl = rawUrl.trim();

            // Handle Render / Heroku / Supabase native URLs: postgres://user:pass@host:5432/dbname
            if (trimmedUrl.startsWith("postgres://") || trimmedUrl.startsWith("postgresql://")) {
                try {
                    String httpUrl = trimmedUrl.startsWith("postgres://") 
                            ? trimmedUrl.replace("postgres://", "http://") 
                            : trimmedUrl.replace("postgresql://", "http://");
                    URI uri = new URI(httpUrl);

                    String host = uri.getHost();
                    int port = uri.getPort() == -1 ? 5432 : uri.getPort();
                    String database = uri.getPath();

                    properties.setUrl("jdbc:postgresql://" + host + ":" + port + database);
                    properties.setDriverClassName("org.postgresql.Driver");

                    if (uri.getUserInfo() != null) {
                        String[] credentials = uri.getUserInfo().split(":", 2);
                        properties.setUsername(credentials[0]);
                        if (credentials.length > 1) {
                            properties.setPassword(credentials[1]);
                        }
                    }
                    return properties;
                } catch (Exception e) {
                    System.err.println("Error parsing DATABASE_URL: " + e.getMessage());
                }
            }

            // If JDBC URL format: jdbc:postgresql://...
            if (trimmedUrl.startsWith("jdbc:")) {
                properties.setUrl(trimmedUrl);
                String driver = System.getenv("SPRING_DATASOURCE_DRIVER");
                properties.setDriverClassName(driver != null ? driver : "org.postgresql.Driver");
                properties.setUsername(System.getenv("SPRING_DATASOURCE_USERNAME"));
                properties.setPassword(System.getenv("SPRING_DATASOURCE_PASSWORD"));
                return properties;
            }
        }

        // Fallback to zero-config embedded H2
        properties.setUrl("jdbc:h2:mem:ghartakdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        properties.setDriverClassName("org.h2.Driver");
        properties.setUsername("sa");
        properties.setPassword("");
        return properties;
    }
}
