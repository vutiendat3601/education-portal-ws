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
                user.getPhone(),
                user.getBio(),
                user.getProfileImg(),
                user.getBackgroundImg(),
                user.getCreatedAt(),
                user.getGender(),
                user.getRole(),
                user.getBirthday());
    }

}
