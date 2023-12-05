package com.employeeWorkReport.repository;

import com.employeeWorkReport.entity.Activity;
import com.employeeWorkReport.entity.CategoryReport;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.data.annotation.Query;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;

import java.util.List;
import java.util.Map;

@Repository
public interface ActivityRepository extends CrudRepository<Activity, Integer> {
        @Query(value = "SELECT a.work_category, SUM(a.hours) AS total_hours, SUM(a.minutes) AS total_minutes " +
                        "FROM activity_table a " +
                        "JOIN form_table f ON f.id = a.form_id " +
                        "JOIN users u ON u.id = f.user_id " +
                        "WHERE f.date BETWEEN :startDate AND :endDate AND f.user_id IN (:userIds) " +
                        "GROUP BY a.work_category", nativeQuery = true)
        List<CategoryReport> getActivitySummaryForUserIdsAndDateRange(
                        List<Integer> userIds,
                        String startDate,
                        String endDate);

        @Query(value = "SELECT a.id , a.work_category , a.hours , a.minutes , a.description , a.form_id " +
                        "FROM activity_table a  " +
                        "WHERE a.form_id = ( " +
                        "select f.id " +
                        "from form_table f " +
                        "where f.user_id = :userId and f.date = :date ) ", nativeQuery = true)
        List<Activity> getActivitySummaryForTheDay(
                        Integer userId,
                        String date);

}
