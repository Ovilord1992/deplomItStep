package com.roculka.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String country;
    private String city;
    private String address;
    private String firstName;
    private String lastName;
    private Integer age;
}
