package com.corp.spring.database.repository;

import com.corp.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    // Optional, Entity, Future
    Optional<Company> findByName(String name);

    //Collection, Strean (batch, close)
    List<Company> findByNameContainingIgnoreCase(String fragment);
}