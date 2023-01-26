package com.corp.spring.database.repository;

import com.corp.spring.database.entity.User;
import com.corp.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);
}
