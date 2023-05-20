package com.datvutech.educationportalws.auth.service;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.datvutech.educationportalws.auth.model.UserAuthDto;
import com.datvutech.educationportalws.user.model.User;

@Service
public class UserAuthDtoMapper implements Function<User, UserAuthDto> {

    @Override
    public UserAuthDto apply(User user) {
        return new UserAuthDto(
                user.getUserId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getRole());
    }

}
