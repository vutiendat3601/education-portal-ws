package com.datvutech.educationportalws.user.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.datvutech.educationportalws.exception.DuplicateResourceException;
import com.datvutech.educationportalws.exception.ResourceNotFoundException;
import com.datvutech.educationportalws.file.service.FileService;
import com.datvutech.educationportalws.file.service.model.FileType;
import com.datvutech.educationportalws.user.model.User;
import com.datvutech.educationportalws.user.model.UserDto;
import com.datvutech.educationportalws.user.model.request.UserRegistrationRequest;
import com.datvutech.educationportalws.user.model.request.UserUpdateRequest;

@Service
public class UserService {

    private final UserRepository userRepo;

    private final UserDtoMapper userDtoMapper;

    private final PasswordEncoder passEncoder;

    private final FileService fileService;

    public UserService(
            UserRepository userRepo,
            UserDtoMapper userDtoMapper,
            PasswordEncoder passEncoder, FileService fileService) {
        this.userRepo = userRepo;
        this.userDtoMapper = userDtoMapper;
        this.passEncoder = passEncoder;
        this.fileService = fileService;
    }

    public UserDto getUser(UUID userId) {
        User user = findUser(userId);
        return userDtoMapper.apply(user);
    }

    public UserDto createUser(UserRegistrationRequest userReq) {
        if (userRepo.existsUserByEmail(userReq.email())) {
            throw new DuplicateResourceException("Email already taken!");
        }
        String pwd = passEncoder.encode(userReq.password());
        User user = new User(userReq.firstName(), userReq.lastName(),
                userReq.email(), pwd, userReq.bio(),
                userReq.phone(), userReq.gender(), userReq.role(), userReq.birthday());
        user.setUserId(UUID.randomUUID());
        user = userRepo.save(user);
        return userDtoMapper.apply(user);
    }

    public UserDto updateUser(UUID userId, UserUpdateRequest userReq) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id [%s] not found!"
                        .formatted(userId)));
        /*
         * if (userRepo.existsUserByEmail(userReq.email())) {
         * throw new DuplicateResourceException("Email already taken!");
         * }
         */
        user.setFirstName(userReq.firstName());
        user.setLastName(userReq.lastName());
        user.setPhone(userReq.phone());
        user.setBio(userReq.bio());
        user.setGender(userReq.gender());
        user = userRepo.save(user);
        return userDtoMapper.apply(user);
    }

    public void deleteUser(UUID userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id [%s] not found!"
                        .formatted(userId)));
        user.setEnabled(false);
        userRepo.save(user);
    }

    public Page<UserDto> getUserPage(int page, int limit) {
        Pageable pageReq = PageRequest.of(page, limit);
        Page<User> pageResult = userRepo.findAll(pageReq);
        Page<UserDto> pageResp = pageResult.map(userDtoMapper::apply);
        return pageResp;
    }

    public UserDto updateProfileImage(UUID userId, MultipartFile profileImage) {

        // If user not found, throw exception at the userService
        User user = findUser(userId);
        String fileName = fileService.saveFile(profileImage, FileType.PROFILE_IMAGE);
        user.setProfileImg(fileName);
        userRepo.save(user);

        return userDtoMapper.apply(user);
    }

    public UserDto updateBackgroundImage(UUID userId, MultipartFile backgroundImage) {

        // If user not found, throw exception at the userService
        User user = findUser(userId);
        String fileName = fileService.saveFile(backgroundImage, FileType.BACKGROUND_IMAGE);
        user.setBackgroundImg(fileName);
        userRepo.save(user);

        return userDtoMapper.apply(user);
    }

    private User findUser(UUID userId) {
        return userRepo.findById(userId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("User with id [%s] not found"
                                .formatted(userId)));
    }

    /*
     * public Resource loadBackgroundImage(UUID userId) {
     * User user = findUser(userId);
     * return fileService.getResouceFile(user.getBackgroundImg(),
     * FileType.BACKGROUND_IMAGE);
     * }
     * 
     * public Resource loadProfileImage(UUID userId) {
     * User user = findUser(userId);
     * return fileService.getResouceFile(user.getProfileImg(),
     * FileType.PROFILE_IMAGE);
     * }
     */

}
