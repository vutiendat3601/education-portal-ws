package com.datvutech.educationportalws.user.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
		UUID userId,
		String firstName,
		String lastName,
		String email,
		String phone,
		String bio,
		String profileImg,
		String backgroundImg,
		LocalDateTime createdAt,
		Gender gender,
		UserRole role,
		LocalDate birthday) {
}
