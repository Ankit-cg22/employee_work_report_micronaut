package com.employeeWorkReport.repository;

import com.employeeWorkReport.entity.Activity;
import com.employeeWorkReport.entity.Form;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface FormRepository extends CrudRepository<Form, Integer> {
    @Query("SELECT f FROM Form f WHERE f.user_id = :userId AND f.submitted = 0")
    List<Form> findUnsubmittedFormsByUserId(Integer userId);
}
