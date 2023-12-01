package com.employeeWorkReport.service;
import com.employeeWorkReport.entity.Form;
import com.employeeWorkReport.entity.User;
import com.employeeWorkReport.repository.FormRepository;
import jakarta.inject.Singleton;

import java.sql.Date;
import java.util.List;

@Singleton
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;

    public FormServiceImpl(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    @Override
    public Form createForm(Integer userId , Date date ) {
        Form form = new Form(userId, date);
        return formRepository.save(form);
    }

    @Override
    public Form getFormById(Integer formId) throws RuntimeException {
        return formRepository.findById(formId).orElseThrow(() -> new RuntimeException("form not found"));
    }

    @Override
    public Form updateForm(Integer formId , Form updatedForm){
        Form prevForm = getFormById(formId);
        prevForm.setUser_id(updatedForm.getUser_id());
        prevForm.setDate(updatedForm.getDate());
        prevForm.setSubmitted(updatedForm.getSubmited());
        System.out.println(prevForm.getSubmited());
        return formRepository.update(prevForm);
    }

    @Override
    public List<Form> getUnsubmittedFormsByUserId(Integer user_id){
        System.out.println("in service");
        try{
            List<Form> forms = formRepository.findUnsubmittedFormsByUserId(user_id);
            System.out.println(forms.size());
            return forms;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}
