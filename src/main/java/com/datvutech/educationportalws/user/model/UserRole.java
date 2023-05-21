package com.datvutech.educationportalws.user.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN, ROLE_LECTURER, ROLE_STUDENT;

    public static final String ROLE_ADMIN_VALUE = "ROLE_ADMIN";
    public static final String ROLE_LECTURER_VALUE = "ROLE_LECTURER";
    public static final String ROLE_STUDENT_VALUE = "ROLE_STUDENT";

    @Override
    public String getAuthority() {
        return toString();
    }
}
