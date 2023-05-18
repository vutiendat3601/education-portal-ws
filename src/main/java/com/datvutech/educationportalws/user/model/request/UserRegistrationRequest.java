package com.datvutech.educationportalws.user.model.request;

import com.datvutech.educationportalws.user.model.Gender;
import com.datvutech.educationportalws.user.model.UserRole;

public record UserRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        UserRole role,
        Gender gender) {
}
