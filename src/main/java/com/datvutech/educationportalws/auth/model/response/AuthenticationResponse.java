package com.datvutech.educationportalws.auth.model.response;

import com.datvutech.educationportalws.user.model.UserDto;

public record AuthenticationResponse(
        String token,
        UserDto user) {
}
