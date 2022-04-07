package com.medicalclinicapp.medicalclinicapp.security.repository;

import com.medicalclinicapp.medicalclinicapp.security.models.Doctor;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.print.Doc;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Doctor findByCnp(String username);
}
