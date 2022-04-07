package com.medicalclinicapp.medicalclinicapp.repository;

import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HospitalizationRepository extends JpaRepository<Hospitalization, String> {

    Hospitalization findByRegistrationNoHospitalization(String registrationNoHospitalization);
}