package com.medicalclinicapp.medicalclinicapp.repository;

import com.medicalclinicapp.medicalclinicapp.models.FisaPatient;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FisaPatientRepository extends JpaRepository<FisaPatient, Long> {
}
