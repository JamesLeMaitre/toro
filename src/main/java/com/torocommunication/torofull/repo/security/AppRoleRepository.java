package com.torocommunication.torofull.repo.security;

import com.torocommunication.torofull.entities.security.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    Optional<AppRole> findByRoleName(String roleName);
}
