package com.roculka.security.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.roculka.security.pojo.LoginRequest;
import com.roculka.security.pojo.SignupRequest;
import com.roculka.security.service.UserAuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


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
