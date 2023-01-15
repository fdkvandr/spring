package com.corp.spring.database.pool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component("pool1")
public class ConnectionPool {

    @Value("${db.username}")
    private final String username;
    @Value("${db.pool.size}")
    private final Integer poolSize;

    public ConnectionPool(@Value("${db.username}") String username, @Value("${db.pool.size}") Integer poolSize) {
        this.username = username;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        System.out.println("Init connection pool");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Clean ConnectionPool");
    }
}
