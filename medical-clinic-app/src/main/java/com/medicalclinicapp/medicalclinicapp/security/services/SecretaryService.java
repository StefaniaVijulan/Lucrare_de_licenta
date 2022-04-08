package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.repository.HospitalizationRepository;
import com.medicalclinicapp.medicalclinicapp.repository.PatientRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Doctor;
import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import com.medicalclinicapp.medicalclinicapp.security.repository.DoctorRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SecretaryService {
    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HospitalizationRepository hospitalizationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Hospitalization> getAllHospitalization(Principal principal){
        List<Hospitalization> hospitalizationList = new ArrayList<>();
        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            {
                if(hospitalizationRepository.findAll().get(i).getEndDateHospitalization() == null)
                    hospitalizationList.add(hospitalizationRepository.findAll().get(i));
            }
        }
        return hospitalizationList;
    }
    public List<Doctor> seeAllDoctors(){
        List<Doctor> doctorList = new ArrayList<>();
        for(int i=0; i<doctorRepository.findAll().size(); i++){
            {
                doctorList.add(doctorRepository.findAll().get(i));
            }
        }
        return doctorList;
    }


    public Patient addPatient(Patient patient) throws Exception {

        if (patientRepository.existsById(patient.getCnp()))
        {
            throw new Exception("Patient exist");

        }
        patient.setRole("PATIENT");
        patient.setPassword(bCryptPasswordEncoder.encode("parola"));
        patientRepository.save(patient);
        System.out.println(patient);
        return patient;
    }
    public Hospitalization addHospitalization(String cnp, String cnpP, Hospitalization hospitalization, Principal principal) throws Exception {
        if(hospitalizationRepository.existsById(hospitalization.getRegistrationNoHospitalization()))
            throw new Exception("Hospitalization exist");
        String username = principal.getName();
        Secretary secretary = this.secretaryRepository.findByCnp(username);
        hospitalization.setSecretary(secretary);
        if(!patientRepository.existsById(cnpP))
            throw new Exception("Patient doesnt exist");
        Patient patient = this.patientRepository.findByCnp(cnpP);
        hospitalization.setPatient(patient);


        if(!doctorRepository.existsById(cnp))
            throw new Exception("You cant assign this doctor because he doesnt exist");
        hospitalization.setDoctor(doctorRepository.findByCnp(cnp));
        hospitalizationRepository.save(hospitalization);
        return hospitalization;
    }
    public String changeHospitalizationDataEnd(String registrationNoHospitalization, Date dateEnd ){

        Optional<Hospitalization> hospitalizationOptional = hospitalizationRepository.findById(registrationNoHospitalization);
        if(!hospitalizationOptional.isPresent()){
            return "This hospitalization doesnt exist";
        }
        System.out.println(dateEnd);
        System.out.println(hospitalizationRepository);
        Hospitalization hospitalization = hospitalizationRepository.findByRegistrationNoHospitalization(registrationNoHospitalization);
        hospitalization.setEndDateHospitalization(dateEnd);
        hospitalizationRepository.save(hospitalization);
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
