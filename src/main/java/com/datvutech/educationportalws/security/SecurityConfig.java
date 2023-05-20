package com.datvutech.educationportalws.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.datvutech.educationportalws.jwt.JwtAuthenticationFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CorsConfig corsConfig;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthFilter,
            CorsConfig corsConfig) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.corsConfig = corsConfig;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfig.corsConfigSource()))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        req -> req
                                .antMatchers(
                                        "/v1/auth/login", "/swagger-ui/**", "/public/**",
                                        "/v3/api-docs/**", "/swagger-ui.html")
                                .permitAll()
                                .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
