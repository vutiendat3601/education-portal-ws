package com.datvutech.educationportalws.user;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datvutech.educationportalws.user.model.UserDto;
import com.datvutech.educationportalws.user.model.request.UserRegistrationRequest;
import com.datvutech.educationportalws.user.model.request.UserUpdateRequest;
import com.datvutech.educationportalws.user.service.UserService;

@CrossOrigin
@RequestMapping("users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public UserDto getUser(
            @PathVariable UUID userId) {
        return userService.getUser(userId);
    }

    @GetMapping
    public Page<UserDto> getUserList(
            @RequestParam int page, @RequestParam int limit) {
        return userService.getUserList(page, limit);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserRegistrationRequest userReq) {
        UserDto user = userService.createUser(userReq);
        return ResponseEntity
                .created(URI.create("/api/users/%s".formatted(user.userId())))
                .body(user);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable UUID userId,
            @RequestBody UserUpdateRequest userReq) {
        userService.updateUser(userId, userReq);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}
