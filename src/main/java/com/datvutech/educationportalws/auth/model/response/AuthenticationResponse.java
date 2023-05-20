package com.datvutech.educationportalws.auth.model.response;

import com.datvutech.educationportalws.auth.model.UserAuthDto;

public record AuthenticationResponse(
        String token,
        UserAuthDto user) {
}
