package ru.roculka.roculka.mail.services;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import ru.roculka.roculka.mail.entity.Mail;
import ru.roculka.roculka.mail.services.ServicesImp.MailServices;


@Service
public class MailServicesImpl implements MailServices {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Override
    public void sendActiveMail(@NonNull Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getFrom());
        message.setTo(mail.getTo());
        message.setSubject(mail.getFrom());
        message.setText(mail.getBody());
        javaMailSender.send(message);
    }
}
