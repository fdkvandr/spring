package com.corp.spring;

import com.corp.spring.config.ApplicationConfiguration;
import com.corp.spring.database.pool.ConnectionPool;
import com.corp.spring.service.CompanyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("prod", "web");
            context.refresh();
            var connectionPool = context.getBean("pool1", ConnectionPool.class);
            var companyService = context.getBean(CompanyService.class);
            System.out.println(companyService.findById(1));
        }
    }
}
