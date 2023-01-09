package com.corp.spring.database.repository;

import com.corp.spring.bpp.Auditing;
import com.corp.spring.bpp.Transaction;
import com.corp.spring.database.pool.ConnectionPool;
import com.corp.spring.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Transaction
@Auditing
public class CompanyRepository implements CrudRepository<Integer, Company> {

    @Autowired
    private ConnectionPool pool1;

    @Autowired
    private List<ConnectionPool> pools;

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
