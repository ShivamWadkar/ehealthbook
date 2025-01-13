package com.ehealthbook.usermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DoctorProfileDTO {

    @NotNull(message = "first name is required")
    @NotBlank(message = "first name is required")
    private String firstName;

    @NotNull(message = "last name is required")
    @NotBlank(message = "last name is required")
    private String lastName;

    @NotNull(message = "gender is required")
    @NotBlank(message = "gender is required")
    private String gender;

    @NotNull(message = "contact number is required")
    @NotBlank(message = "contact number is required")
    private String contactNumber;

    @NotNull(message = "qualifications is required")
    @NotBlank(message = "qualifications is required")
    private String qualifications;

    @NotNull(message = "specialization is required")
    @NotBlank(message = "specialization is required")
    private String specialization;

    @NotNull(message = "experience is required")
    private Float experienceYears;

    private String clinicAddress;
}
