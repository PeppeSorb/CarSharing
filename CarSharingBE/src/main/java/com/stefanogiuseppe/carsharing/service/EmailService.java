package com.stefanogiuseppe.carsharing.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendVerificationEmail(String toEmail, String verificationLink) throws MessagingException {
       /* MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("Verify Your Email");

        String text = "Click the link below to verify your email:\n" + verificationLink;
        helper.setText(text, true);

        javaMailSender.send(message);*/

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("giuseppesorbello98.ct@gmail.com");
        message.setTo(toEmail);//DA PERSONALIZZARE

        message.setText(verificationLink);
        System.out.println(message);
        javaMailSender.send(message);
    }
}

