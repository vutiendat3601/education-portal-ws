package com.datvutech.educationportalws.user.service;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.datvutech.educationportalws.user.model.User;
import com.datvutech.educationportalws.user.model.UserDto;

@Service
public class UserDtoMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCreatedAt());
    }

}
