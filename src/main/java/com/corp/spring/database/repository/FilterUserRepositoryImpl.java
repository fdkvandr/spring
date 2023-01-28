package com.corp.spring.database.repository;

import com.corp.spring.database.entity.Role;
import com.corp.spring.database.entity.User;
import com.corp.spring.database.querydsl.QPredicates;
import com.corp.spring.dto.PersonalInfo;
import com.corp.spring.dto.UserFilter;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static com.corp.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    private final static String FIND_BY_COMPANY_AND_ROLE = """
                                                           SELECT
                                                                firstname,
                                                                lastname,
                                                                birth_date
                                                           FROM users
                                                           WHERE company_id = ?
                                                                AND role = ?
                                                           """;

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE, (rs, rowNum) -> new PersonalInfo(rs.getString("firstname"), rs.getString("lastname"), rs.getDate("birth_date")
                .toLocalDate()), companyId, role.name());
    }

    @Override
    public List<User> findAllByFilter(UserFilter filter) {

        Predicate predicate = QPredicates.builder()
                .add(filter.firstname(), user.firstname::containsIgnoreCase)
                .add(filter.lastname(), user.lastname::containsIgnoreCase)
                .add(filter.birthDate(), user.birthDate::before)
                .build();

        return new JPAQuery<User>(entityManager).select(user).from(user).where(predicate).fetch();
    }
}
