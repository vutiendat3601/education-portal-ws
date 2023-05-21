package com.datvutech.educationportalws.user.model.request;

import java.time.LocalDate;

import com.datvutech.educationportalws.user.model.Gender;
import com.datvutech.educationportalws.user.model.UserRole;

public record UserRegistrationRequest(
        String firstName,
        String lastName,
        String phone,
        String email,
        String bio,
        String password,
        UserRole role,
        Gender gender,
        LocalDate birthday) {
}
