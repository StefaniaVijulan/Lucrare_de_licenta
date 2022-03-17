package com.medicalclinicapp.medicalclinicapp.security.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse{
    private final String jwt;
}
