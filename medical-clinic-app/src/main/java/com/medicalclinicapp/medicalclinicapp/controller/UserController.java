package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.MedicalClinicAppApplication;
import com.medicalclinicapp.medicalclinicapp.security.dto.LoginRequest;
import com.medicalclinicapp.medicalclinicapp.security.dto.LoginResponse;
import com.medicalclinicapp.medicalclinicapp.security.dto.RegisterRequest;
import com.medicalclinicapp.medicalclinicapp.security.models.Role;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.medicalclinicapp.medicalclinicapp.security.models.Role.DOCTOR;

@RestController
public class UserController {
        @Autowired
        UserService userService;


        @PostMapping(value = "/register")
        public User register(@RequestBody RegisterRequest registerRequest) {
            return userService.registerUser(registerRequest);
        }

        @PostMapping(value = "/login")
        public User login(@RequestBody LoginRequest loginRequest) {

            //return userService.loginUser(loginRequest);
            LoginResponse loginResponse = new LoginResponse();
            User user = userService.loginUser(loginRequest.getCnp(), loginRequest.getPassword());
            loginResponse.setCnp(user.getCnp());
            MedicalClinicAppApplication.setCurrentUser(user);
            System.out.println("Connected successfully");
            return user;
        }

        @GetMapping(path = "/logout")
        public String logoutUser() {
            MedicalClinicAppApplication.setCurrentUser(null);
            return "You logged out!";

        }

    }

