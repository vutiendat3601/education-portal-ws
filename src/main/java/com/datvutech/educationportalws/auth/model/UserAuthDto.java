package com.datvutech.educationportalws.auth.model;

import java.util.UUID;

import com.datvutech.educationportalws.user.model.UserRole;

public record UserAuthDto(
        UUID userId,
        String fullName,
        String email,
        UserRole role) {

}
