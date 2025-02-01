package com.ehealthbook.authentication.controller;

import com.ehealthbook.authentication.dto.ApiResponse;
import com.ehealthbook.authentication.dto.SignupRequest;
import com.ehealthbook.authentication.dto.AuthenticationRequest;
import com.ehealthbook.authentication.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Validated
@AllArgsConstructor
public class AuthenticationController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody AuthenticationRequest request) throws Exception {
        String jwtToken = authService.validateUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(jwtToken));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<String>> signup(@Valid @RequestBody SignupRequest signupRequest) {
        System.out.println(signupRequest.getEmail() + " " + signupRequest.getPassword() + " " + signupRequest.getRole());
        String message = authService.registerUser(signupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(message));
    }
}
