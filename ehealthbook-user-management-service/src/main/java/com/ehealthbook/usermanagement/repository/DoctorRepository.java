package com.ehealthbook.usermanagement.repository;

import com.ehealthbook.usermanagement.entity.DoctorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorProfile, Long> {
    Optional<DoctorProfile> findByUserId(UUID uuid);
}
