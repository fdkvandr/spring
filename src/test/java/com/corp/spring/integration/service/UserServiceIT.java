package com.corp.spring.integration.service;

import com.corp.spring.database.pool.ConnectionPool;
import com.corp.spring.integration.annotation.IT;
import com.corp.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

@IT
@RequiredArgsConstructor
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
    void test() {

    }

    @Test
    void test2() {

    }

    @Test
    void test3() {

    }
}
