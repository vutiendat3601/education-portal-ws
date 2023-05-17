package com.datvutech.educationportalws.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "certifications")
@Entity
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer certificationId;

    @ManyToOne
    @JoinColumn
    private User user;

    private String title;

    private String issuingAuthority;

    private LocalDate dateIssued;

    private LocalDate expirationDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
