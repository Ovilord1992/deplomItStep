package com.roculka.security.repo;

import com.roculka.security.entity.ERole;
import com.roculka.security.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(ERole role);

}
