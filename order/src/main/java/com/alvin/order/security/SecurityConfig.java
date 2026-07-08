package com.alvin.order.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) {

        this.jwtAuthenticationFilter =
                jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                "/h2-console/**"
                        )
                        .permitAll()

                        // USER + ADMIN
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/order/**"
                        )
                        .hasAnyRole(
                                "USER",
                                "ADMIN"
                        )

                        // USER + ADMIN
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/order/**"
                        )
                        .hasAnyRole(
                                "USER",
                                "ADMIN"
                        )

                        // ADMIN ONLY
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/order/**"
                        )
                        .hasRole("ADMIN")

                        .anyRequest()
                        .authenticated()
                )

                .headers(headers ->
                        headers.frameOptions(
                                frame -> frame.disable()
                        )
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}