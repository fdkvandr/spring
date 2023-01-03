package com.corp.spring;

import com.corp.spring.database.pool.ConnectionPool;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationRunner {

    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        var connectionPool = context.getBean("p1", ConnectionPool.class);
        System.out.println(connectionPool);
    }
}
