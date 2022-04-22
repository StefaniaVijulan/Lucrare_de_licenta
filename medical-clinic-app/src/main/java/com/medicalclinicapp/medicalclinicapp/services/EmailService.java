package com.medicalclinicapp.medicalclinicapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendmail(String toEmail,
                         String subjectEmail,
                         String bodyEmail){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("vijulans2000@gmail.com");
        msg.setTo(toEmail);
        msg.setText(bodyEmail);
        msg.setSubject(subjectEmail);

        mailSender.send(msg);
        System.out.println("Mail sent successfully");
    }
}
