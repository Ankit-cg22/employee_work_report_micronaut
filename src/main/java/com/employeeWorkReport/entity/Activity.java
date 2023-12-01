package com.employeeWorkReport.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "activity_table")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    String work_category;

    @NotBlank
    Integer hours;

    @NotBlank
    Integer minutes;

    String description;

    @NotBlank
    Integer form_id;

    public Activity() {
    }

    public Activity(int id, String work_category, Integer hours, Integer minutes,String description, Integer form_id) {
        this.id = id;
        this.work_category = work_category;
        this.hours = hours;
        this.minutes = minutes;
        this.description = description;
        this.form_id = form_id;
    }

    public Activity(String work_category, Integer hours, Integer minutes,String description, Integer form_id) {
        this.work_category = work_category;
        this.hours = hours;
        this.minutes = minutes;
        this.description = description;
        this.form_id = form_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWork_category() {
        return work_category;
    }

    public void setWork_category(String work_category) {
        this.work_category = work_category;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getForm_id() {
        return form_id;
    }

    public void setForm_id(Integer form_id) {
        this.form_id = form_id;
    }
}
