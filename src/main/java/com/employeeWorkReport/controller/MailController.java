package com.employeeWorkReport.controller;

import com.employeeWorkReport.service.MailService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.server.cors.CrossOrigin;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller("/api/mail")
public class MailController {
    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @Post("/sendMail")
    public HttpResponse<Map<String, Object>> sendMail(@Body Map<String, Object> requestBody) {
        String firstName = (String) requestBody.get("firstName");
        String toEmail = (String) requestBody.get("toEmail");

        String dateStr = (String) requestBody.get("date");
        LocalDate localDate = LocalDate.parse(dateStr);
        Date date = Date.valueOf(localDate);

        String formLink = (String) requestBody.get("formLink");

        Map<String, Object> returnObject = new HashMap<>();
        try {
            mailService.sendMail(firstName, toEmail, date, formLink);
            returnObject.put("message", "Mail sent successfully.");
            return HttpResponse.ok(returnObject);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            returnObject.put("message", "Unable to send mail!!");
            return HttpResponse.serverError(returnObject);
        }

    }
}
