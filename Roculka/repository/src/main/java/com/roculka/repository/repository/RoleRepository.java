package com.roculka.repository.repository;

import com.roculka.repository.entity.ERole;
import com.roculka.repository.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(ERole role);
}
