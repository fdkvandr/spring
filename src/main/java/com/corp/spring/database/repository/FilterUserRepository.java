package com.corp.spring.database.repository;

import com.corp.spring.database.entity.Role;
import com.corp.spring.database.entity.User;
import com.corp.spring.dto.PersonalInfo;
import com.corp.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter filter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);
}
