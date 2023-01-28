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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

import static com.corp.spring.database.entity.QUser.user;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final static String FIND_BY_COMPANY_AND_ROLE = """
           SELECT
                firstname,
                lastname,
                birth_date
           FROM users
           WHERE company_id = ?
                AND role = ?
           """;

    private final static String UPDATE_COMPANY_AND_ROLE = """
          UPDATE users
          SET company_id = ?,
              role = ?
          WHERE id = ?
          """;

    private final static String UPDATE_COMPANY_AND_ROLE_NAMED = """
          UPDATE users
          SET company_id = :companyId,
              role = :role
          WHERE id = :id
          """;
    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE, (rs, rowNum) -> new PersonalInfo(rs.getString("firstname"), rs.getString("lastname"), rs.getDate("birth_date")
                .toLocalDate()), companyId, role.name());
    }

    @Override
    public int[] updateCompanyAndRole(List<User> users) {
        List<Object[]> args = users.stream()
                .map(user -> new Object[]{
                        user.getCompany().getId(),
                        user.getRole().name(),
                        user.getId()
                }).toList();
        return jdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE, args);
    }

    @Override
    public int[] updateCompanyAndRoleNamed(List<User> users) {
        MapSqlParameterSource[] args = users.stream()
                .map(user -> Map.of(
                        "companyId", user.getCompany().getId(),
                        "role", user.getRole().name(),
                        "id", user.getId()
                ))
                .map(MapSqlParameterSource::new)
                .toArray(MapSqlParameterSource[]::new);
        return namedParameterJdbcTemplate.batchUpdate(UPDATE_COMPANY_AND_ROLE_NAMED,args);
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
