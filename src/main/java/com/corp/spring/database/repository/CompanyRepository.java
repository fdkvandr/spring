package com.corp.spring.database.repository;

import com.corp.spring.bpp.Auditing;
import com.corp.spring.bpp.Transaction;
import com.corp.spring.database.entity.Company;
import com.corp.spring.database.pool.ConnectionPool;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transaction
@Auditing
@Repository
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool pool1;

    private final List<ConnectionPool> pools;
    @Value("${db.pool.size}")
    private final Integer poolSize;

    @PostConstruct
    private void init() {
        System.out.println("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        System.out.println("findById method...");
        return Optional.of(new Company(id));
    }

    @Override
    public void delete(Company entity) {
        System.out.println("delete method...");
    }
}
