package com.datvutech.educationportalws.user.model;

import java.time.LocalDate;
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

    private String phone;

    private String pwd;

    private String profileImg;

    private String backgroundImg;

    private LocalDateTime createdAt;

    private String bio;

    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    private LocalDate birthday;

    /* #: Constructors */
    public User() {
    }

    public User(String firstName,
            String lastName,
            String email,
            String pwd,
            String bio,
            String phone,
            Gender gender,
            UserRole role,
            LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pwd = pwd;
        this.gender = gender;
        this.role = role;
        this.bio = bio;
        this.phone = phone;
        this.birthday = birthday;
    }

    /* # Constructors */

    /* #: Triggers */
    @PrePersist
    public void prePersist() {
        phone = phone == null ? "" : phone;
        profileImg = profileImg == null ? "" : profileImg;
        backgroundImg = backgroundImg == null ? "" : backgroundImg;
        createdAt = LocalDateTime.now();
        bio = bio == null ? "" : bio;
        enabled = true;
        birthday = birthday == null ? LocalDate.of(1970, 1, 1) : birthday;
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
