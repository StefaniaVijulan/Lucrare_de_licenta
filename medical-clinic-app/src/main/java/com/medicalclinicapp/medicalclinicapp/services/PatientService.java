package com.medicalclinicapp.medicalclinicapp.services;

import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PatientService{

    @Autowired
    private PatientRepository patientRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Patient loginPatient(String cnp, String password){
        if (!patientRepository.existsByCnp(cnp)) {
            throw new IllegalStateException("Cnp doesnt exist");
        }

        Patient userProfile = patientRepository.findByCnp(cnp);
        String pass = userProfile.getPassword();
        if (!bCryptPasswordEncoder.matches(password, pass)) {
            throw new IllegalStateException("Cnp doesnt exist");

        }
        System.out.println(userProfile);
        return userProfile;
    }
}
