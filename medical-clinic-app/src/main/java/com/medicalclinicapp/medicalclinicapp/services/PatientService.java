package com.medicalclinicapp.medicalclinicapp.services;

import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.repository.PatientRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void createPatient(Patient patient) throws IOException {
        //verificam daca un user cu email-ul respectiv se gaseste deja
        Optional<Patient> patientOptional = patientRepository.findByCnpPatient(patient.getCnpPatient());
        if (patientOptional.isPresent()) {
            throw new IllegalStateException("Cnp taken");
        }
        patient.setPasswordPatient(bCryptPasswordEncoder.encode(patient.getPasswordPatient()));
        patientRepository.save(patient);
    };
}
