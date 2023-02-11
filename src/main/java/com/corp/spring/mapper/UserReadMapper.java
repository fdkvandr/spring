package com.corp.spring.mapper;

import com.corp.spring.database.entity.User;
import com.corp.spring.dto.CompanyReadDto;
import com.corp.spring.dto.UserReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto map(User object) {

        CompanyReadDto companyReadDto = Optional.ofNullable(object.getCompany())
                .map(companyReadMapper::map)
                .orElse(null);

        return new UserReadDto(
            object.getId(),
            object.getUsername(),
            object.getBirthDate(),
            object.getFirstname(),
            object.getLastname(),
            object.getRole(),
            object.getImage(),
            companyReadDto
        );
    }
}
