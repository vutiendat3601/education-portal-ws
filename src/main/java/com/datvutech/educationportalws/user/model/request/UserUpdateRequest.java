package com.datvutech.educationportalws.user.model.request;

import java.time.LocalDate;

import com.datvutech.educationportalws.user.model.Gender;

public record UserUpdateRequest(
		String firstName,
		String lastName,
		String bio,
		String phone,
		Gender gender,
		LocalDate birthday) {
}
