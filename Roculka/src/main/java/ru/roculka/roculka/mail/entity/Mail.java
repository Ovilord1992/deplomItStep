package ru.roculka.roculka.mail.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Mail {
    private String from;
    private String to;
    private String title;
    private String body;

}
