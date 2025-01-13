package com.ehealthbook.usermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "doctor_profile")
public class DoctorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "qualifications", nullable = false)
    private String qualifications;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @Column(name = "experience_years", nullable = false)
    private Float experienceYears;

    @Column(name = "clinic_address")
    private String clinicAddress;
}
