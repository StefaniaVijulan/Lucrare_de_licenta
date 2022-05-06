package com.medicalclinicapp.medicalclinicapp.security.repository;


import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretaryRepository extends JpaRepository<Secretary, Long> {

    boolean existsByCnp(String cnp);

    Secretary findByCnp(String cnp);
}