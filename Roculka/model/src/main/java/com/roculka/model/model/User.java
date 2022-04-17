package com.roculka.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String email;
    private String phone;
    private String country;
    private String city;
    private String address;
    private Boolean isEnabled;
    private Boolean isActive;
    private String activeCode;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime dateBirth;
    private Integer age;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime date_create;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDateTime date_update;
}
