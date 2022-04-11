package com.medicalclinicapp.medicalclinicapp.security.repository;

import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretaryRepository extends JpaRepository<Secretary, String> {
    Secretary findByCnp(String cnp);
}
