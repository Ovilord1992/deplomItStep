package ru.roculka.roculka.mail.services.ServicesImp;

import com.sun.istack.NotNull;
import ru.roculka.roculka.mail.entity.Mail;

public interface MailServices {
    void sendActiveMail(@NotNull Mail mail);
}
