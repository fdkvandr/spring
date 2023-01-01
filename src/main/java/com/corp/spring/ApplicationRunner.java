package com.corp.spring;

import com.corp.spring.database.pool.ConnectionPool;
import com.corp.spring.database.repository.CompanyRepository;
import com.corp.spring.database.repository.UserRepository;
import com.corp.spring.ioc.Container;
import com.corp.spring.service.UserService;

public class ApplicationRunner {

    public static void main(String[] args) {
        Container container = new Container();

        var connectionPool = container.get(ConnectionPool.class);
        var userRepository = container.get(UserRepository.class);
        var companyRepository = container.get(CompanyRepository.class);
        var userService = container.get(UserService.class);
        // TODO 29.12.2022 userService
    }
}
