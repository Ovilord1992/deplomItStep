package com.roculka.mail.service;

import com.roculka.mail.model.MailModel;
import org.jetbrains.annotations.NotNull;

public interface MailService {
    void send(@NotNull MailModel mail);
}
