package com.datvutech.educationportalws.user.model;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
		UUID userId,
		String firstName,
		String lastName,
		String email,
		LocalDateTime createdAt,
		Gender gender,
		UserRole role) {
}
