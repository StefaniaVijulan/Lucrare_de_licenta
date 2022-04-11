package com.medicalclinicapp.medicalclinicapp.security.repository;

import com.medicalclinicapp.medicalclinicapp.security.models.Curant;
import com.medicalclinicapp.medicalclinicapp.security.models.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurantRepository extends JpaRepository<Curant, String> {
    Curant findByCnp(String cnp);
}