package com.employeeWorkReport.tasks;

import com.employeeWorkReport.entity.Form;
import com.employeeWorkReport.entity.User;
import com.employeeWorkReport.service.FormService;
import com.employeeWorkReport.service.MailService;
import com.employeeWorkReport.service.UserService;
import com.employeeWorkReport.utils.Constants;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Singleton
public class DailyMails {

    private final UserService userService;
    private final FormService formService;
    private final MailService mailService;

    public DailyMails(UserService userService, FormService formService, MailService mailService) {
        this.userService = userService;
        this.formService = formService;
        this.mailService = mailService;
    }

//        @Scheduled(fixedDelay = "5s")
    @Scheduled(cron = "0 0 18 * * MON-FRI")
    void execute(){
        // get email of all users
        List<User> userList = userService.getAllUsers();

        // for each mail
        for(User user : userList ){
            // get current date
            Date date = Date.valueOf(LocalDate.now());

            // create entries in form table
            Form form = formService.createForm(user.getId() , date);

            // take the form_id
            // add the form_id to the form submission link . ex: server.com/submitForm/formId
            String formLink = Constants.BASE_FRONTEND_URL + "/form/" + form.getId();

            // send the link in mail
            // send the mail
            mailService.sendMail(user.getFirstName() , user.getEmailId() , date , formLink);

        }


    }
}
