package com.employeeWorkReport.repository;

import com.employeeWorkReport.entity.User;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import org.hibernate.annotations.SQLUpdate;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User , Integer> {
    List<User> findAll();
    User findByEmailId(String emailId);

    @Query("SELECT u.email_id FROM users")
    List<String> findAllEmails();

    @Query("UPDATE User SET manager_id = :managerid WHERE id =:userid")
    void updateManagerId(Integer userid , Integer managerid);

    @Query("UPDATE User SET manager_id = null WHERE id =:userid")
    void setManagerIdToNull(Integer userid );

    @Query("SELECT u from User u WHERE u.manager_id IS NULL")
    List<User> getUsersWithNoManager();

    @Query("SELECT u from User u WHERE u.manager_id=:managerid AND u.id !=:managerid")
    List<User> getUsersByManagerId(Integer managerid);
}
