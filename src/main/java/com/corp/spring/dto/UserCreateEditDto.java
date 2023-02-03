package com.corp.spring.dto;

import com.corp.spring.database.entity.Role;
import lombok.Value;

import java.time.LocalDate;

@Value
public class UserCreateEditDto {

    String username;
    LocalDate birthDate;
    String firstname;
    String lastname;
    Role role;
    Integer companyId; // Нам достаточно только айдишника компании чтобы создать пользователя. Вся сущность компании не нужна
}
