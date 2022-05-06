package com.medicalclinicapp.medicalclinicapp.security.repository;

import com.medicalclinicapp.medicalclinicapp.security.models.Moderator;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
    Optional<Moderator> findByCnp(String cnp);
}
