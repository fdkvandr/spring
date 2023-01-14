package com.corp.spring.config;

import com.corp.spring.database.pool.ConnectionPool;
import com.corp.spring.database.repository.CrudRepository;
import com.corp.spring.database.repository.UserRepository;
import com.corp.web.config.WebConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Component;

@ImportResource("classpath:application.xml")
@Import(WebConfiguration.class)
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.corp.spring", useDefaultFilters = false, includeFilters = {@Filter(type = FilterType.ANNOTATION, value = Component.class), @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class), @Filter(type = FilterType.REGEX, pattern = "com\\..+Repository"),})
public class ApplicationConfiguration {

    @Bean
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String username) {
        return new ConnectionPool(username, 20);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("test-pool", 20);
    }

    @Bean
    @Profile("prod")
    public UserRepository userRepository2(ConnectionPool pool2) {
        return new UserRepository(pool2);
    }

    @Bean
    public UserRepository userRepository3() {
        ConnectionPool connectionPool = pool3();
        ConnectionPool connectionPool2 = pool3();
        return new UserRepository(pool3());
    }
}
