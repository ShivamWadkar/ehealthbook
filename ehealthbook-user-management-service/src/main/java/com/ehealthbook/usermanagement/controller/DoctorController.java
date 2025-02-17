package com.ehealthbook.usermanagement.controller;

import com.ehealthbook.usermanagement.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<DoctorProfileDTO>> createProfile(@RequestHeader("X-User-Id") String userId,
                                                          @RequestHeader("X-User-Role") String role,
                                                          @Valid @RequestBody DoctorProfileDTO doctorProfileDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(doctorService.createProfile(userId, doctorProfileDTO)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<DoctorProfileDTO>> getProfile(@RequestHeader("X-User-Id") String userId,
                                                        @RequestHeader("X-User-Role") String role) {
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(doctorService.getProfile(userId)));
    }
}
