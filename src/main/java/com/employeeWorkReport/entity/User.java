package com.employeeWorkReport.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String firstName;

    private String lastName;

    @NotBlank
    private String emailId;

    @NotBlank
    private LocalTime workHourStart;

    @NotBlank
    private LocalTime workHourEnd;

    @NotBlank
    private String password;

    private Integer manager_id;

    public User() {
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public User(int id, String firstName, String lastName, String emailId, LocalTime workHourStart, LocalTime workHourEnd, String password, Integer manager_id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.workHourStart = workHourStart;
        this.workHourEnd = workHourEnd;
        this.password = password;
        this.manager_id = manager_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public LocalTime getWorkHourStart() {
        return workHourStart;
    }

    public void setWorkHourStart(LocalTime workHourStart) {
        this.workHourStart = workHourStart;
    }

    public LocalTime getWorkHourEnd() {
        return workHourEnd;
    }

    public void setWorkHourEnd(LocalTime workHourEnd) {
        this.workHourEnd = workHourEnd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
