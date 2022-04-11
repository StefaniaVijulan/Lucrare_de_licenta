package com.medicalclinicapp.medicalclinicapp.security.repository;

import com.medicalclinicapp.medicalclinicapp.security.models.Hematolog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HematologRepository  extends JpaRepository<Hematolog, String> {
    Hematolog findByCnp(String cnp);
}