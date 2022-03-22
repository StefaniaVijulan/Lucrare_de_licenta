package com.medicalclinicapp.medicalclinicapp.repository;

import com.medicalclinicapp.medicalclinicapp.security.models.User;
import org.springframework.data.repository.CrudRepository;

public interface SaveUserDataToDBRepository  extends CrudRepository<User, String> {
}
