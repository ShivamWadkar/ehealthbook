package com.ehealthbook.usermanagement.service;

import com.ehealthbook.usermanagement.dto.PatientProfileDTO;
import com.ehealthbook.usermanagement.entity.PatientProfile;
import com.ehealthbook.usermanagement.exception.ApiException;
import com.ehealthbook.usermanagement.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientService {

    private PatientRepository patientRepository;

    public PatientProfileDTO createProfile(String userId, PatientProfileDTO patientProfileDTO) {
        var profile = patientRepository.findByUserId(UUID.fromString(userId));
        if (profile.isPresent()) {
            throw new ApiException("User already exists", HttpStatus.BAD_REQUEST);
        }
        PatientProfile patientProfile = new PatientProfile();
        patientProfile.setUserId(UUID.fromString(userId));
        patientProfile.setFirstName(patientProfileDTO.getFirstName());
        patientProfile.setLastName(patientProfileDTO.getLastName());
        patientProfile.setGender(patientProfileDTO.getGender());
        patientProfile.setContactNumber(patientProfileDTO.getContactNumber());
        patientProfile.setBloodGroup(patientProfileDTO.getBloodGroup());
        patientProfile.setDateOfBirth(patientProfileDTO.getDateOfBirth());

        PatientProfile createdProfile = patientRepository.save(patientProfile);
        return PatientProfileDTO.builder()
                .firstName(createdProfile.getFirstName())
                .lastName(createdProfile.getLastName())
                .gender(createdProfile.getGender())
                .contactNumber(createdProfile.getContactNumber())
                .bloodGroup(createdProfile.getBloodGroup())
                .dateOfBirth(createdProfile.getDateOfBirth())
                .build();
    }

    public PatientProfileDTO getProfile(String userId) {
        var patientProfile = patientRepository.findByUserId(UUID.fromString(userId));
        if (patientProfile.isEmpty()) {
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        }
        PatientProfile fetchedProfile = patientProfile.get();
        return PatientProfileDTO.builder()
                .firstName(fetchedProfile.getFirstName())
                .lastName(fetchedProfile.getLastName())
                .gender(fetchedProfile.getGender())
                .contactNumber(fetchedProfile.getContactNumber())
                .dateOfBirth(fetchedProfile.getDateOfBirth())
                .bloodGroup(fetchedProfile.getBloodGroup())
                .build();
    }
}
