package com.ehealthbook.usermanagement.service;

import com.ehealthbook.usermanagement.dto.DoctorProfileDTO;
import com.ehealthbook.usermanagement.entity.DoctorProfile;
import com.ehealthbook.usermanagement.exception.ApiException;
import com.ehealthbook.usermanagement.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DoctorService {

    private DoctorRepository doctorRepository;

    public DoctorProfileDTO createProfile(String userId, DoctorProfileDTO doctorProfileDTO) {
        var profile = doctorRepository.findByUserId(UUID.fromString(userId));
        if (profile.isPresent()) {
            throw new ApiException("User already exists", HttpStatus.BAD_REQUEST);
        }

        DoctorProfile doctorProfile = new DoctorProfile();
        doctorProfile.setUserId(UUID.fromString(userId));
        doctorProfile.setFirstName(doctorProfileDTO.getFirstName());
        doctorProfile.setLastName(doctorProfileDTO.getLastName());
        doctorProfile.setGender(doctorProfileDTO.getGender());
        doctorProfile.setContactNumber(doctorProfileDTO.getContactNumber());
        doctorProfile.setQualifications(doctorProfileDTO.getQualifications());
        doctorProfile.setSpecialization(doctorProfileDTO.getSpecialization());
        doctorProfile.setExperienceYears(doctorProfileDTO.getExperienceYears());
        doctorProfile.setClinicAddress(doctorProfileDTO.getClinicAddress());

        DoctorProfile createdProfile = doctorRepository.save(doctorProfile);
        return DoctorProfileDTO.builder()
                .firstName(createdProfile.getFirstName())
                .lastName(createdProfile.getLastName())
                .gender(createdProfile.getGender())
                .contactNumber(createdProfile.getContactNumber())
                .qualifications(createdProfile.getQualifications())
                .specialization(createdProfile.getSpecialization())
                .experienceYears(createdProfile.getExperienceYears())
                .clinicAddress(createdProfile.getClinicAddress())
                .build();
    }

    public DoctorProfileDTO getProfile(String userId) {
        var doctorProfile = doctorRepository.findByUserId(UUID.fromString(userId));
        if (doctorProfile.isEmpty()) {
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        }

        DoctorProfile fetchedProfile = doctorProfile.get();
        return DoctorProfileDTO.builder()
                .firstName(fetchedProfile.getFirstName())
                .lastName(fetchedProfile.getLastName())
                .gender(fetchedProfile.getGender())
                .contactNumber(fetchedProfile.getContactNumber())
                .qualifications(fetchedProfile.getQualifications())
                .specialization(fetchedProfile.getSpecialization())
                .experienceYears(fetchedProfile.getExperienceYears())
                .clinicAddress(fetchedProfile.getClinicAddress())
                .build();
    }
}
