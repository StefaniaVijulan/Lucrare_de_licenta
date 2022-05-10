package com.medicalclinicapp.medicalclinicapp.security.repository;

import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardiologRepository extends JpaRepository<Cardiolog, Long> {

    boolean existsByCnp(String cnp);

    Cardiolog findByCnp(String cnp);
}