package com.ehealthbook.authentication.filter;

import com.ehealthbook.authentication.security.CustomUserDetails;
import com.ehealthbook.authentication.security.JwtUtil;
import com.ehealthbook.authentication.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private CustomUserDetailsService customUserDetailsService;

    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String userId = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                userId = jwtUtil.extractUserId(jwt);
            } catch (ExpiredJwtException e) {
                System.out.println("JWT token expired");
            }
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            CustomUserDetails userDetails = (CustomUserDetails) this.customUserDetailsService.loadUserByUsername(userId);
            if (jwtUtil.validateToken(jwt, userDetails.getId())) {
                UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
