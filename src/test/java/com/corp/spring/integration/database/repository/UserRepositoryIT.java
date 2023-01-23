package com.corp.spring.integration.database.repository;

import com.corp.spring.database.entity.Role;
import com.corp.spring.database.entity.User;
import com.corp.spring.database.repository.UserRepository;
import com.corp.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@IT
@RequiredArgsConstructor
class UserRepositoryIT {

    private final UserRepository userRepository;


    @Test
    void checkPageable() {
        var pageable = PageRequest.of(1, 2, Sort.by("id"));
        var allUsers = userRepository.findAllBy(pageable);
        assertThat(allUsers).hasSize(2);
    }

    @Test
    void checkSort() {
        var sortBy = Sort.sort(User.class);
        var sort = sortBy.by(User::getFirstname).and(sortBy.by(User::getLastname));

        var allUsers = userRepository.findFirst3ByBirthDateBefore(now(), sort);
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFirst3() {
        var sortByFistAndLastName = Sort.by("firstname").and(Sort.by("lastname"));
        var allUsers = userRepository.findFirst3ByBirthDateBefore(now(), sortByFistAndLastName);
        assertThat(allUsers).hasSize(3);
    }

    @Test
    void checkFirst() {
        var firstUser = userRepository.findFirstByOrderByIdDesc();
        assertTrue(firstUser.isPresent());
        firstUser.ifPresent(user -> assertEquals(5L, user.getId()));
    }

    @Test
    void checkUpdate() {
        var ivan = userRepository.findById(1L);
        ivan.ifPresent(user -> assertSame(Role.ADMIN, user.getRole()));

        var resultCount = userRepository.updateRole(Role.USER, 1L, 5L);
        assertEquals(2, resultCount);

        // var companyName = ivan.get().getCompany().getName();

        var sameIvan = userRepository.findById(1L);
        sameIvan.ifPresent(user -> assertSame(Role.USER, user.getRole()));
    }

    @Test
    void checkQueries() {
        var users = userRepository.findAllBy("a", "ov");
        assertThat(users).hasSize(3);
    }
}