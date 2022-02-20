package com.medicalclinicapp.medicalclinicapp.security.repository;

import com.medicalclinicapp.medicalclinicapp.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByCnp (String cnpUser);
    User findUserByCnpAndPassword(String emailUser, String passwordUser);
    boolean existsByCnp(String cnp);
    boolean existsByCnpAndPassword(String cnp, String passsword);
}
