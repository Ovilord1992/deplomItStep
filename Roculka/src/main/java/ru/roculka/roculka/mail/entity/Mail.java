package ru.roculka.roculka.mail.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mail {
    private String from;
    private String to;
    private String title;
    private String body;
}
