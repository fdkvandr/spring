package com.corp.spring.database.repository;

import com.corp.spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u " +
            "WHERE u.firstname LIKE %:firstname% " +
            "AND u.lastname LIKE %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(value = "SELECT u.* FROM USERS u WHERE username = :username", nativeQuery = true)
    List<User> findAllBy(String username);
}
