package com.corp.spring.config;

import com.corp.spring.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@EnableEnversRepositories(basePackageClasses = ApplicationRunner.class)
public class AuditConfiguration {


    @Bean
    public AuditorAware<String> auditorAware() {
        // На реальной практике мы тут будем лезть в SecurityContext.getCurrentUser() и брать .getId() или getEmail() и так далее
        return () -> Optional.of("Andrey");
    }
}
