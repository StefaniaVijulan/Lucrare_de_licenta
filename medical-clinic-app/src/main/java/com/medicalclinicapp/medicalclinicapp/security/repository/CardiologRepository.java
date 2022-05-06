package com.medicalclinicapp.medicalclinicapp.security.repository;

import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardiologRepository extends JpaRepository<Cardiolog, Long> {

    Cardiolog findByCnp(String cnp);

    boolean existsByCnp(String cnp);
}