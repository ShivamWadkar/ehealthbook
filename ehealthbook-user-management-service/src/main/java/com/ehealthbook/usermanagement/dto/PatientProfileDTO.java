package com.ehealthbook.usermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class PatientProfileDTO {

    @NotNull(message = "first name is required")
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotNull(message = "last name is required")
    @NotBlank(message = "last name is required")
    private String lastName;

    @NotNull(message = "date if birth is required")
    @Past
    private Date dateOfBirth;

    @NotNull(message = "gender is required")
    @NotBlank(message = "gender is required")
    private String gender;

    @NotNull(message = "contact number is required")
    @NotBlank(message = "First name is required")
    private String contactNumber;

    private String bloodGroup;
}
