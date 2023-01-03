package com.corp.spring;

import com.corp.spring.database.pool.ConnectionPool;
import com.corp.spring.database.repository.CompanyRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        var connectionPool = context.getBean("p1", ConnectionPool.class);
        var companyRepository = context.getBean("companyRepository", CompanyRepository.class);
        System.out.println(connectionPool);
    }
}
