package com.medicalclinicapp.medicalclinicapp.repository;


import com.medicalclinicapp.medicalclinicapp.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<Patient, String> {
    Patient findByCnp(String cnp);
}
