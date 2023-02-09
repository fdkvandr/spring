package com.corp.spring.validation.impl;

import com.corp.spring.dto.UserCreateEditDto;
import com.corp.spring.validation.UserInfo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateEditDto> { // Могли бы укзаать вместо UserCreateEditDto какой то интерфейс в котором есть getFirstname() и getLastname() и наследоваться от него, и тогда бы было еще гибче

    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value.getFirstname()) || StringUtils.hasText(value.getLastname());
    }
}
