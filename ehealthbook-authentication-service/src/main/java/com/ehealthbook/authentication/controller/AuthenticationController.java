package com.ehealthbook.authentication.controller;

import com.ehealthbook.authentication.dto.SignupRequest;
import com.ehealthbook.authentication.dto.AuthenticationRequest;
import com.ehealthbook.authentication.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<String> login(@Valid @RequestBody AuthenticationRequest request) throws Exception {
        String userName = authService.validateUser(request);
        return ResponseEntity.ok(userName);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest signupRequest) {
        String message = authService.registerUser(signupRequest);
        return ResponseEntity.ok(message);
    }
}
