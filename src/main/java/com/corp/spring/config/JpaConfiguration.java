package com.corp.spring.config;

import com.corp.spring.config.condition.JpaCondition;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Conditional(JpaCondition.class)
@Configuration
@Slf4j
public class JpaConfiguration {

    // @Bean
    // @ConfigurationProperties(prefix = "db")
    // public DatabaseProperties databaseProperties() {
    //     return new DatabaseProperties();
    // }

    @PostConstruct
    void init() {
        log.info("Jpa configuration is enabled");
    }
}
