package com.ehealthbook.authentication.service;

import com.ehealthbook.authentication.dto.AuthenticationRequest;
import com.ehealthbook.authentication.dto.SignupRequest;
import com.ehealthbook.authentication.entity.Role;
import com.ehealthbook.authentication.entity.User;
import com.ehealthbook.authentication.exception.ApiException;
import com.ehealthbook.authentication.repository.RoleRepository;
import com.ehealthbook.authentication.repository.UserRepository;
import com.ehealthbook.authentication.security.CustomUserDetails;
import com.ehealthbook.authentication.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;

    private RoleRepository roleRepository;

    private CustomUserDetailsService customUserDetailsService;

    private JwtUtil jwtUtil;

    public String validateUser(AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new ApiException("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }

        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(request.getUsername());

        return jwtUtil.generateToken(userDetails.getId(), userDetails.getUsername(), userDetails.getRole());
    }

    public String registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new IllegalArgumentException("Username is already taken");
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new IllegalArgumentException("Email is already taken");
        }

        // Fetch the role by name from the database
        Role role = roleRepository.findByName(signupRequest.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found: " + signupRequest.getRole()));

        // Create the user and set the role
        User user = User.builder()
                .username(signupRequest.getUsername())
                .password(new BCryptPasswordEncoder().encode(signupRequest.getPassword()))
                .email(signupRequest.getEmail())
                .role(role)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }
}
