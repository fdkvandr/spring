package com.corp.spring.database.repository;

import com.corp.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // Optional, Entity, Future
    Optional<Company> findByName(@Param("name2") String name);

    //Collection, Strean (batch, close)
    List<Company> findByNameContainingIgnoreCase(String fragment);
}