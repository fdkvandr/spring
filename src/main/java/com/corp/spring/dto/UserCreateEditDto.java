package com.corp.spring.dto;

import com.corp.spring.database.entity.Role;
import com.corp.spring.validation.UserInfo;
import com.corp.spring.validation.group.CreateAction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Value
@FieldNameConstants
@UserInfo(groups = CreateAction.class)
public class UserCreateEditDto {

    @Email
    String username;

    LocalDate birthDate;

    @Size(min = 3, max = 64)
    String firstname;

    String lastname;

    Role role;

    Integer companyId;

    MultipartFile image;
}
