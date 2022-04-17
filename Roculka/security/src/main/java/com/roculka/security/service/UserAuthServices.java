package com.roculka.security.service;

import com.roculka.security.config.jwt.JwtUtils;
import com.roculka.security.entity.ERole;
import com.roculka.security.entity.Roles;
import com.roculka.security.entity.User;
import com.roculka.security.pojo.JwtResponse;
import com.roculka.security.pojo.LoginRequest;
import com.roculka.security.pojo.MessageResponse;
import com.roculka.security.pojo.SignupRequest;
import com.roculka.security.repo.RoleRepository;
import com.roculka.security.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAuthServices {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    //Регистрируем пользователя
    public ResponseEntity<?> registerUser(SignupRequest signupRequest){
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is exist"));
        }

        User user = new User(
                signupRequest.getUsername(),
                passwordEncoder.encode(signupRequest.getPassword()),
                signupRequest.getEmail()
        );

        Set<String> reqRoles = signupRequest.getRoles();
        Set<Roles> roles = new HashSet<>();

        if (reqRoles == null) {
            Roles userRole = roleRepository
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles.add(userRole);
        } else {
            reqRoles.forEach(r -> {
                switch (r) {
                    case "admin" -> {
                        Roles adminRole = roleRepository
                                .findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Roles modRole = roleRepository
                                .findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error, Role MODERATOR is not found"));
                        roles.add(modRole);
                    }
                    default -> {
                        Roles userRole = roleRepository
                                .findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                        roles.add(userRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        user.setIsEnabled(false);
        user.setIsActive(true);
        String acceptCode = RandomString.make(64);
        user.setActiveCode(acceptCode);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }


    //Логиним пользователя и отдает токен доступа (в случае если потверждена почта)
    public ResponseEntity<?> authUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

}
