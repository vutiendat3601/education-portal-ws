package com.datvutech.educationportalws.user.model.request;

public record UserUpdateRequest(
        String firstName,
        String lastName,
        String email) {

}
