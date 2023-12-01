package com.employeeWorkReport.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Entity
@Table(name = "form_table")
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    Integer user_id ;

    @NotBlank
    Date date;

    boolean submitted;

    public Form() {
    }

    public Form(int id , Integer user_id, Date date) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.submitted = false;
    }

    public Form(Integer user_id, Date date) {
        this.user_id = user_id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean getSubmited() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }
}
