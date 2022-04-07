package com.medicalclinicapp.medicalclinicapp.services;


import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.repository.HospitalizationRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Doctor;
import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.DoctorRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.SecretaryRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.UserRepository;
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

    public Hospitalization addHospitalization(Hospitalization hospitalization, Principal principal) {

        String username = principal.getName();
        Secretary secretary = this.secretaryRepository.findByCnp(username);

        hospitalization.setSecretary(secretary);

        hospitalizationRepository.save(hospitalization);
        return hospitalization;
    }
    public String changeHospitalizationDataEnd(String registrationNoHospitalization, Date dateEnd ){
        Optional<Hospitalization> hospitalizationOptional = hospitalizationRepository.findById(registrationNoHospitalization);
        if(!hospitalizationOptional.isPresent()){
            return "This hospitalization doesnt exist";
        }
        Hospitalization hospitalization = hospitalizationRepository.findByRegistrationNoHospitalization(registrationNoHospitalization);
        hospitalization.setEndDateHospitalization(dateEnd);
        return "Change Hospitalization Data End";
    }
    public String changeHospitalizationNumberOfHospitalization(String registrationNoHospitalization, Integer numberOfHospitalization){
        Optional<Hospitalization> hospitalizationOptional = hospitalizationRepository.findById(registrationNoHospitalization);
        if(!hospitalizationOptional.isPresent()){
           return "This hospitalization doesnt exist";
        }
        Hospitalization hospitalization = hospitalizationRepository.findByRegistrationNoHospitalization(registrationNoHospitalization);
        hospitalization.setNumberOfHospitalization(numberOfHospitalization);
        return "Change Hospitalization Data End";
    }
}
