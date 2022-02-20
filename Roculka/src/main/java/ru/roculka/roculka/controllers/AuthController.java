package ru.roculka.roculka.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.roculka.roculka.config.jwt.JwtUtils;
import ru.roculka.roculka.entity.ERole;
import ru.roculka.roculka.entity.Roles;
import ru.roculka.roculka.entity.User;
import ru.roculka.roculka.pojo.JwtResponse;
import ru.roculka.roculka.pojo.LoginRequest;
import ru.roculka.roculka.pojo.MessageResponse;
import ru.roculka.roculka.pojo.SignupRequest;
import ru.roculka.roculka.repo.RoleRepository;
import ru.roculka.roculka.repo.UserRepository;
import ru.roculka.roculka.service.UserAuthServices;
import ru.roculka.roculka.service.UserDetailsImpl;

@RestController
@RequestMapping("/api/auth")
//запросы от js находящихся на других портах
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AuthController {

    private final UserAuthServices userAuthServices;

    @GetMapping("/code")
    public void codeChecker(@RequestParam String code){

    }

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest){
        return userAuthServices.authUser(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest){
        return userAuthServices.registerUser(signupRequest);
    }
}
