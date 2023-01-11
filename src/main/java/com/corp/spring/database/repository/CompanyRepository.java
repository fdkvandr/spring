package com.corp.spring.database.repository;

import com.corp.spring.bpp.Auditing;
import com.corp.spring.bpp.Transaction;
import com.corp.spring.database.pool.ConnectionPool;
import com.corp.spring.entity.Company;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Transaction
@Auditing
@Repository
public class CompanyRepository implements CrudRepository<Integer, Company> {

    private final ConnectionPool pool1;

    private final List<ConnectionPool> pools;
    private final Integer poolSize;

    public CompanyRepository(ConnectionPool pool1, List<ConnectionPool> pools, @Value("${db.pool.size}") Integer poolSize) {
        this.pool1 = pool1;
        this.poolSize = poolSize;
        this.pools = pools;
    }

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
