package com.corp.spring.database.repository;

import com.corp.spring.database.entity.Role;
import com.corp.spring.database.entity.User;
import com.corp.spring.dto.PersonalInfo2;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>, FilterUserRepository{

    @Query("select u from User u " +
            "where u.firstname like %:firstname% " +
            "and u.lastname like %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(value = "SELECT u.* FROM USERS u WHERE username = :username", nativeQuery = true)
    List<User> findAllBy(String username);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u " +
            "set u.role = :role " +
            "where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    Optional<User> findFirstByOrderByIdDesc();

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
    @Lock(LockModeType.PESSIMISTIC_READ)
    List<User> findFirst3ByBirthDateBefore(LocalDate birtdhDate, Sort sort);

    @EntityGraph(attributePaths = {"company"})
    @Query(value = "select u from User u",
    countQuery = "select count(distinct u.firstname) from User u")
    Page<User> findAllBy(Pageable pageable);

    // List<PersonalInfo> findAllByCompanyId(Integer companyId);

    // <T> List<T> findAllByCompanyId(Integer companyId, Class<T> clazz);

    @Query(value = "SELECT firstname, lastname, birth_date birthDate " +
            "FROM users " +
            "WHERE company_id = :companyId",
    nativeQuery = true)
    List<PersonalInfo2> findAllByCompanyId(Integer companyId);

}
