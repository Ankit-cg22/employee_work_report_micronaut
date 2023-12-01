package com.employeeWorkReport.service;
import jakarta.inject.Singleton;

import java.sql.Date;
import java.time.LocalTime;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Singleton
public class MailServiceImpl implements MailService{
    String from = "ankitdas.pk@gmail.com";
    // DEV credentials
    final String username = "ankitdas.pk@gmail.com";
    final String password = "gpmu aypz rhso ayeu";

    // PROD credentials
//    final String username = "ankitdas.cg22@gmail.com";
//    final String password = "epjf ikgc xgbp ijkd";

    String host = "smtp.gmail.com";



    @Override
    public void sendMail(String firstName , String toEmail,  Date date , String formLink) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        String htmlContent = "<html><body><p>Dear " + firstName + ", <br> Hope your day went well .<br>"+
                "Please fill the following form to provide details about the tasks you did today (" + date + ") .  <br>" +
                "<br>" + formLink + "<br>"+
                "<br>Thank you,<br>"+
                "Daily Work Report Team</p>"+
                "</body></html>";

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

            message.setSubject("Daily work report");

            message.setContent(htmlContent, "text/html");
            Transport.send(message);

            System.out.println("Email has been sent successfully.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
