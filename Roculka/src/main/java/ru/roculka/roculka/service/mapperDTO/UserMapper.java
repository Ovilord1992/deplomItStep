package ru.roculka.roculka.service.mapperDTO;

import org.springframework.stereotype.Component;
import ru.roculka.roculka.dto.UserDTO;
import ru.roculka.roculka.entity.User;

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
