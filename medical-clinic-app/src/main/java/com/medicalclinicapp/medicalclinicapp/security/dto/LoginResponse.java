package com.medicalclinicapp.medicalclinicapp.security.dto;


import com.medicalclinicapp.medicalclinicapp.security.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse{
    private final String jwt;
    private final Role role;
}
