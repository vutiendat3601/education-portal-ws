package com.datvutech.educationportalws.user.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datvutech.educationportalws.user.model.User;
import com.datvutech.educationportalws.user.model.UserRole;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsUserByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query(" SELECT u FROM User u WHERE u.role != '" + UserRole.ROLE_ADMIN_VALUE + "' ")
    Page<User> findAll(Pageable pageable);
}
