package com.datvutech.educationportalws.auth.model.request;

public record UsernamePasswordAuthentcationRequest(
		String username,
		String password) {
}
