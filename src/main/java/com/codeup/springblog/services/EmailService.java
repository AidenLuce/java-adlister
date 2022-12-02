package com.codeup.springblog.services;

import com.codeup.springblog.modals.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Value("${spring.mail.from}")
    private String from;

    @Autowired
    public JavaMailSender emailSender;

    public void prepareAndSend(User user, String subject, String body){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setFrom(from);
        msg.setSubject(subject);
        msg.setText(body);

        try {
            this.emailSender.send(msg);
        } catch (MailException mex){
            System.err.println(mex.getMessage());
        }
    }
}
