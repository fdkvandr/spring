package com.corp.spring.service;

import com.corp.spring.database.entity.Company;
import com.corp.spring.database.repository.CrudRepository;
import com.corp.spring.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<Integer, Company> companyRepository;
}
