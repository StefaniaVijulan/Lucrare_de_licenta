package com.medicalclinicapp.medicalclinicapp.repository;


import com.medicalclinicapp.medicalclinicapp.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository  extends JpaRepository<Patient, Long> {

    boolean existsByCnp(String cnp);

    Patient findByCnp(String cnpP);


    Patient findByFirstName(String firstName);
}
