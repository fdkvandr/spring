package com.corp.spring.database.repository;

import com.corp.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // @Query(name="Company.findByName")
    @Query("SELECT c FROM Company c " +
            "JOIN FETCH c.locales cl " +
            "WHERE c.name = :name2")
    Optional<Company> findByName(@Param("name2") String name);

    List<Company> findByNameContainingIgnoreCase(String fragment);
}