package com.medicalclinicapp.medicalclinicapp.services;


import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.repository.HospitalizationRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import com.medicalclinicapp.medicalclinicapp.security.repository.DoctorRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalizationService {
    private final HospitalizationRepository hospitalizationRepository;

    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private DoctorRepository doctorRepository;

}
