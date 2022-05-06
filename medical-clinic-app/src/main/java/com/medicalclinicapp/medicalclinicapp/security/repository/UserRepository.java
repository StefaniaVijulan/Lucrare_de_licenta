package com.medicalclinicapp.medicalclinicapp.security.repository;

import com.medicalclinicapp.medicalclinicapp.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByCnp(String cnp);


    boolean existsByCnp(String cnp);
}
