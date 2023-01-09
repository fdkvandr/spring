package com.corp.spring.service;

import com.corp.spring.database.repository.CrudRepository;
import com.corp.spring.database.repository.UserRepository;
import com.corp.spring.entity.Company;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;


    public UserService(UserRepository userRepository, CrudRepository<Integer, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}
