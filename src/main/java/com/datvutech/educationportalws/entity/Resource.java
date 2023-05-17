package com.datvutech.educationportalws.entity;

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
@Table(name = "resources")
@Entity
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resourceId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String title;

    private String description;

    private String url;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
