/* package com.datvutech.educationportalws.enrollment;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.datvutech.educationportalws.course.Course;
import com.datvutech.educationportalws.user.model.User;

import lombok.Data;

@Data
@Table(name = "enrollments")
@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrollmentId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

    private LocalDate enrollmentDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum EnrollmentStatus {
        ENROLLED, COMPLETED, DROPPED
    }
}
 */