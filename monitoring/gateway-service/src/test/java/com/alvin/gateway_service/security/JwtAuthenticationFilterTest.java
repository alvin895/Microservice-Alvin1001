package com.alvin.gateway_service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

class JwtAuthenticationFilterTest {

    @Test
    void shouldPopulateSecurityContextWhenTokenIsValid() throws Exception {
        JwtUtil jwtUtil = mock(JwtUtil.class);
        when(jwtUtil.validateToken("abc123")).thenReturn(true);
        when(jwtUtil.extractUsername("abc123")).thenReturn("admin");
        when(jwtUtil.extractRole("abc123")).thenReturn("ROLE_ADMIN");

        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getServletPath()).thenReturn("/api/produk/1");
        when(request.getHeader("Authorization")).thenReturn("Bearer abc123");

        SecurityContextHolder.clearContext();
        filter.doFilter(request, response, chain);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assertNotNull(authentication);
        assertEquals("admin", authentication.getPrincipal());
        verify(chain).doFilter(request, response);
    }
}
