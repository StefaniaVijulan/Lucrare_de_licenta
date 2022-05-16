package com.medicalclinicapp.medicalclinicapp.security.dto;

import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginPatientResponse {
    private final String jwt;
    private final Patient patient;
}
