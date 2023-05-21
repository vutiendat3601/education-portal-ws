package com.datvutech.educationportalws.user;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.datvutech.educationportalws.file.service.FileService;
import com.datvutech.educationportalws.user.model.UserDto;
import com.datvutech.educationportalws.user.model.request.UserRegistrationRequest;
import com.datvutech.educationportalws.user.model.request.UserUpdateRequest;
import com.datvutech.educationportalws.user.service.UserService;

@RequestMapping("v1/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService, FileService fileService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    @PreAuthorize("isAuthenticated()")
    public UserDto getUser(
            @PathVariable UUID userId) {
        return userService.getUser(userId);
    }

    @GetMapping
    public Page<UserDto> getUserList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int limit) {
        return userService.getUserPage(page, limit);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserRegistrationRequest userReq) {
        UserDto user = userService.createUser(userReq);
        return ResponseEntity
                .created(URI.create("api/users/%s".formatted(user.userId())))
                .body(user);
    }

    @PutMapping("{userId}/profile-image")
    public ResponseEntity<UserDto> updateProfileImage(
            @PathVariable UUID userId,
            @RequestParam("file") MultipartFile profileImage) {
        UserDto userResp = userService.updateProfileImage(userId, profileImage);
        return ResponseEntity.ok(userResp);
    }

    @PutMapping("{userId}/background-image")
    public ResponseEntity<UserDto> updateBackgroundImage(
            @PathVariable UUID userId,
            @RequestParam("file") MultipartFile backgroundImage) {
        UserDto userResp = userService.updateBackgroundImage(userId, backgroundImage);
        return ResponseEntity.ok(userResp);
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserDto> updateUserDetails(
            @PathVariable UUID userId,
            @RequestBody UserUpdateRequest userReq) {
        UserDto user = userService.updateUser(userId, userReq);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}
