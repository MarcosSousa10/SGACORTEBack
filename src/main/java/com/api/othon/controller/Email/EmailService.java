package com.api.othon.controller.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.api.othon.model.MailConfig;

import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private MailConfig mailConfig;

    private JavaMailSender emailSender;

    @jakarta.annotation.PostConstruct
    public void init() {
        updateMailSender();
    }

    // Torna o método público para que ele possa ser chamado pelo controller
    public void updateMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailConfig.getHost());
        mailSender.setPort(mailConfig.getPort());
        mailSender.setUsername(mailConfig.getUsername());
        mailSender.setPassword(mailConfig.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", String.valueOf(mailConfig.isAuth()));
        props.put("mail.smtp.starttls.enable", String.valueOf(mailConfig.isStarttls()));

        this.emailSender = mailSender;
    }

    public void enviarEmail(String destinatario, String assunto, String corpo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject(assunto);
        message.setText(corpo);
        emailSender.send(message);
    }
}
