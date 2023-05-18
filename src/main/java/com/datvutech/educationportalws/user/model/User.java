package com.datvutech.educationportalws.user.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Table(name = "users")
@Entity
public class User implements UserDetails {
    @Id
    private UUID userId;

    private String firstName;

    private String lastName;

    private String email;

    private String pwd;

    private LocalDateTime createdAt;

    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    /* #: Constructors */
    public User() {
    }

    public User(String firstName,
            String lastName,
            String email,
            String pwd,
            Gender gender,
            UserRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pwd = pwd;
        this.gender = gender;
        this.role = role;
    }

    /* # Constructors */

    /* #: Triggers */
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        enabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    /* # Triggers */
}
