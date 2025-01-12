package com.ehealthbook.usermanagement.controller;

import com.ehealthbook.usermanagement.dto.PatientProfileDTO;
import com.ehealthbook.usermanagement.service.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@AllArgsConstructor
@RestController
@Validated
@RequestMapping("/api/user/patient")
public class PatientController {

    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientProfileDTO> createProfile(@RequestHeader("X-User-Id") String userId,
                                                @RequestHeader("X-User-Role") String role,
                                                @Valid  @RequestBody PatientProfileDTO patientProfileDTO) {
        PatientProfileDTO createdProfile = patientService.createProfile(userId, patientProfileDTO);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PatientProfileDTO> getProfile(@RequestHeader("X-User-Id") String userId,
                                                        @RequestHeader("X-User-Role") String role) {
        return new ResponseEntity<>(patientService.getProfile(userId), HttpStatus.OK);
    }

}
