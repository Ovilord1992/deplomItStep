package com.roculka.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roculka.repository.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersion = 1L;
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private Boolean isEnable;
    private Boolean isActive;
    private Collection<? extends GrantedAuthority> authorities;


    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getIsEnabled(),
                user.getIsActive(),
                authorities);
    }

    //При необходимости добавить поля, при добовлении новых условий
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId(){
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getEmail(){
        return email;
    }


    //Истек ли срок действия аккаунта пользователя
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //заблокирован или разблокирован пользователь
    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    //Истек ли срок действия пароля
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //Включен или отключен пользователь
    @Override
    public boolean isEnabled() {
        return isEnable;
    }
}
