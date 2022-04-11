package com.medicalclinicapp.medicalclinicapp.services;


import com.medicalclinicapp.medicalclinicapp.repository.HospitalizationRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalizationService {
    private final HospitalizationRepository hospitalizationRepository;

    @Autowired
    private SecretaryRepository secretaryRepository;


}
