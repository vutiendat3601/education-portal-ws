package com.datvutech.educationportalws.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.datvutech.educationportalws.auth.model.request.UsernamePasswordAuthentcationRequest;
import com.datvutech.educationportalws.auth.model.response.AuthenticationResponse;
import com.datvutech.educationportalws.jwt.JwtUtil;
import com.datvutech.educationportalws.user.model.User;
import com.datvutech.educationportalws.user.model.UserDto;
import com.datvutech.educationportalws.user.service.UserDtoMapper;
import com.datvutech.educationportalws.user.service.UserRepository;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDtoMapper userDtoMapper;
    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager, UserDtoMapper userDtoMapper,
            UserRepository userRepo, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDtoMapper = userDtoMapper;
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse login(UsernamePasswordAuthentcationRequest userPwdReq) {
        authenticationManager
                .authenticate(
                        UsernamePasswordAuthenticationToken
                                .unauthenticated(userPwdReq.username(), userPwdReq.password()));

        User user = userRepo.findByEmail(userPwdReq.username()).get();
        UserDto userDto = userDtoMapper.apply(user);
        String token = jwtUtil.issueToken(userDto.email(), userDto.role().toString());
        return new AuthenticationResponse(token, userDto);
    }

}
