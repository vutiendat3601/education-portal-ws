package com.datvutech.educationportalws.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datvutech.educationportalws.auth.model.request.UsernamePasswordAuthentcationRequest;
import com.datvutech.educationportalws.auth.model.response.AuthenticationResponse;
import com.datvutech.educationportalws.auth.service.AuthenticationService;

@RestController
@RequestMapping("v1/auth")
public class AuthenticationController {

    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UsernamePasswordAuthentcationRequest request) {
        AuthenticationResponse authResp = authService.login(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, authResp.token())
                .body(authResp); 
    }
}
