package com.datvutech.educationportalws.auth.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datvutech.educationportalws.user.model.User;
import com.datvutech.educationportalws.user.service.UserRepository;

@Service
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepo;

    private final PasswordEncoder passEncoder;

    public UsernamePasswordAuthenticationProvider(UserRepository userRepo, PasswordEncoder passEncoder) {
        this.userRepo = userRepo;
        this.passEncoder = passEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName().toString();
        String password = authentication.getCredentials().toString();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException(email + " is not assigned with any user!"));
        if (passEncoder.matches(password, user.getPwd())) {
            return UsernamePasswordAuthenticationToken
                    .authenticated(user, "", user.getAuthorities());
        }
        throw new BadCredentialsException("Wrong password!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }

}
