package com.medicalclinicapp.medicalclinicapp.security.repository;

import com.medicalclinicapp.medicalclinicapp.security.models.Imagist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagistRepository  extends JpaRepository<Imagist, Long> {
    Imagist findByCnp(String cnp);

    boolean existsByCnp(String cnp);
}