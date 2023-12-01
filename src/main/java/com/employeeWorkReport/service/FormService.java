package com.employeeWorkReport.service;

import com.employeeWorkReport.entity.Form;

import java.sql.Date;
import java.util.List;

public interface FormService {
    Form createForm(Integer userId , Date date);
    Form getFormById(Integer id);
    Form updateForm(Integer formId , Form updatedForm);
    List<Form> getUnsubmittedFormsByUserId(Integer user_id);
}
