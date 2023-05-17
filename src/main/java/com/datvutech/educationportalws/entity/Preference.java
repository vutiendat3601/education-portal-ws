package com.datvutech.educationportalws.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "preferences")
@Entity
public class Preference {

    @Id
    @GeneratedValue()
    private Integer preferenceId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private NotificationSetting notificationSetting;

    private String accessibilityOptions; // font_size, color, constrast

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum NotificationSetting {
        EMAIL, PUSH, NONE
    }
}
