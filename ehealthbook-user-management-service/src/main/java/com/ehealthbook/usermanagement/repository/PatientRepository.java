package com.ehealthbook.usermanagement.repository;

import com.ehealthbook.usermanagement.dto.PatientProfileDTO;
import com.ehealthbook.usermanagement.entity.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<PatientProfile, Long> {
    Optional<PatientProfile> findByUserId(UUID userId);
}
