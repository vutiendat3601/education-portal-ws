package com.datvutech.educationportalws.user.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datvutech.educationportalws.exception.DuplicateResourceException;
import com.datvutech.educationportalws.exception.ResourceNotFoundException;
import com.datvutech.educationportalws.user.model.User;
import com.datvutech.educationportalws.user.model.UserDto;
import com.datvutech.educationportalws.user.model.request.UserRegistrationRequest;
import com.datvutech.educationportalws.user.model.request.UserUpdateRequest;

@Service
public class UserService {

    private final UserRepository userRepo;

    private final UserDtoMapper userDtoMapper;

    private final PasswordEncoder passEncoder;

    public UserService(
            UserRepository userRepo,
            UserDtoMapper userDtoMapper,
            PasswordEncoder passEncoder) {
        this.userRepo = userRepo;
        this.userDtoMapper = userDtoMapper;
        this.passEncoder = passEncoder;
    }

    public UserDto getUser(UUID userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with id [%s] not found"
                                .formatted(userId)));
        return userDtoMapper.apply(user);
    }

    public UserDto createUser(UserRegistrationRequest userReq) {
        if (userRepo.existsUserByEmail(userReq.email())) {
            throw new DuplicateResourceException("Email already taken!");
        }
        String pwd = passEncoder.encode(userReq.password());
        User user = new User(userReq.firstName(), userReq.lastName(), userReq.email(), pwd, userReq.gender(),
                userReq.role());
        user.setUserId(UUID.randomUUID());
        user = userRepo.save(user);
        return userDtoMapper.apply(user);
    }

    public void updateUser(UUID userId, UserUpdateRequest userReq) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id [%s] not found!"
                        .formatted(userId)));
        if (userRepo.existsUserByEmail(userReq.email())) {
            throw new DuplicateResourceException("Email already taken!");
        }
        user.setEmail(userReq.email());
        user.setFirstName(userReq.firstName());
        user.setLastName(userReq.lastName());
        userRepo.save(user);
    }

    public void deleteUser(UUID userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id [%s] not found!"
                        .formatted(userId)));
        user.setEnabled(false);
        userRepo.save(user);
    }

    public Page<UserDto> getUserList(int page, int limit) {
        Pageable pageReq = PageRequest.of(page - 1, limit);
        Page<User> pageResult = userRepo.findAll(pageReq);
        Page<UserDto> pageResp = pageResult.map(userDtoMapper::apply);
        return pageResp;
    }
}
