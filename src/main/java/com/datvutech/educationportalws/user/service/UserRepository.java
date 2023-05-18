package com.datvutech.educationportalws.user.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datvutech.educationportalws.user.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsUserByEmail(String email);

    Optional<User> findByEmail(String email);
}
