package com.roculka.mail.service;

import com.roculka.mail.model.MailModel;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultMailService implements MailService {

    private final JavaMailSenderImpl javaMailSender;


    @Override
    public void send(@NotNull MailModel mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getFrom());
        message.setTo(mail.getTo());
        message.setSubject(mail.getFrom());
        message.setText(mail.getBody());
        javaMailSender.send(message);
    }
}
