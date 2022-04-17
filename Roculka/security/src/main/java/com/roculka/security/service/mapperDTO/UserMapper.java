package com.roculka.security.service.mapperDTO;

import com.roculka.security.dto.UserDTO;
import com.roculka.security.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {
    public static UserDTO createUserDto(User user){
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getCountry(),
                user.getCity(),
                user.getAddress(),
                user.getFirstName(),
                user.getLastName(),
                user.getAge()
        );
    }
}
