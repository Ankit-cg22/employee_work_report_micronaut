package com.employeeWorkReport.service;

import java.sql.Date;
import java.time.LocalTime;

public interface MailService {
    void sendMail(String firstName , String toEmail, Date date, String formLink );
}
