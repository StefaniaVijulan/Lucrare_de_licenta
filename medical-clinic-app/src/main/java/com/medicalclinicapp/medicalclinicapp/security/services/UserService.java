package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.exception.MyException;
import com.medicalclinicapp.medicalclinicapp.security.dto.LoginRequest;
import com.medicalclinicapp.medicalclinicapp.security.dto.RegisterRequest;
import com.medicalclinicapp.medicalclinicapp.security.models.Role;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public  User registerUser (RegisterRequest registerRequest){
        //verify if the new user already exists
        if(userRepository.existsByCnp(registerRequest.getCnp())){
            throw new MyException("This user already exists in system", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User newUser = new User();

        newUser.setCnp(registerRequest.getCnp());
        newUser.setFirstName(registerRequest.getFirstName());
        newUser.setLastName(registerRequest.getLastName());
        newUser.setSpecialty(registerRequest.getSpecialty());
        newUser.setPassword(registerRequest.getPassword());
        newUser.setRole(Role.DOCTOR);

        userRepository.save(newUser);
        return newUser;
    }

    public User loginUser(String cnp, String password){
        if(userRepository.existsByCnpAndPassword(cnp, password)){
            return  userRepository.findUserByCnpAndPassword(cnp,password);
        }
        throw new MyException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);

    }

}
