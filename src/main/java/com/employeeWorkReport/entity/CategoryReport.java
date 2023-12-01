package com.employeeWorkReport.entity;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class CategoryReport {
    private String work_category;
    private Integer total_hours;
    private Integer total_minutes;

    public CategoryReport(String work_category, Integer total_hours, Integer total_minutes) {
        this.work_category = work_category;
        this.total_hours = total_hours;
        this.total_minutes = total_minutes;
    }

    public String getWork_category() {
        return work_category;
    }

    public void setWork_category(String work_category) {
        this.work_category = work_category;
    }

    public Integer getTotal_hours() {
        return total_hours;
    }

    public void setTotal_hours(Integer total_hours) {
        this.total_hours = total_hours;
    }

    public Integer getTotal_minutes() {
        return total_minutes;
    }

    public void setTotal_minutes(Integer total_minutes) {
        this.total_minutes = total_minutes;
    }
}
