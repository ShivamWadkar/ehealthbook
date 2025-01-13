package com.ehealthbook.usermanagement.controller;

import com.ehealthbook.usermanagement.dto.DoctorProfileDTO;
import com.ehealthbook.usermanagement.service.DoctorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@AllArgsConstructor
@RestController
@RequestMapping("/api/user/doctor")
public class DoctorController {

    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<DoctorProfileDTO> createProfile(@RequestHeader("X-User-Id") String userId,
                                                          @RequestHeader("X-User-Role") String role,
                                                          @Valid @RequestBody DoctorProfileDTO doctorProfileDTO) {
        return new ResponseEntity<>(doctorService.createProfile(userId, doctorProfileDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<DoctorProfileDTO> getProfile(@RequestHeader("X-User-Id") String userId,
                                                        @RequestHeader("X-User-Role") String role) {
        return new ResponseEntity<>(doctorService.getProfile(userId), HttpStatus.OK);
    }
}
