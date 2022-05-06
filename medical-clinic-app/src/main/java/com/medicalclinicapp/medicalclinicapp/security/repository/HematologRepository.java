package com.medicalclinicapp.medicalclinicapp.security.repository;

import com.medicalclinicapp.medicalclinicapp.security.models.Hematolog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HematologRepository  extends JpaRepository<Hematolog, Long> {
    Hematolog findByCnp(String cnp);

    boolean existsByCnp(String cnp);
}