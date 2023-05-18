package com.datvutech.educationportalws.user.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_STUDENT, ROLE_TEACHER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return toString();
    }
}
