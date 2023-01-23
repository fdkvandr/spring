package com.corp.spring.integration.database.repository;

import com.corp.spring.database.entity.Role;
import com.corp.spring.database.repository.UserRepository;
import com.corp.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@IT
@RequiredArgsConstructor
class UserRepositoryIT {

    private final UserRepository userRepository;

    @Test
    void checkUpdate() {
        var ivan = userRepository.findById(1L);
        assertSame(Role.ADMIN, ivan.get().getRole());

        var resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        // var companyName = ivan.get().getCompany().getName();

        var sameIvan = userRepository.findById(1L);
        assertSame(Role.USER, sameIvan.get().getRole());
    }

    @Test
    void checkQueries() {
        var users = userRepository.findAllBy("a", "ov");
        Assertions.assertThat(users).hasSize(3);
    }
}