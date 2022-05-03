package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.repository.HospitalizationRepository;
import com.medicalclinicapp.medicalclinicapp.repository.PatientRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.CardiologRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.SecretaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class SecretaryService {
    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private CardiologRepository cardiologRepository;

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
    public List<User> seeAllCurant(){
        List<User> generalists = new ArrayList<>();
        for(int i=0; i<cardiologRepository.findAll().size(); i++){
            {
                generalists.add(cardiologRepository.findAll().get(i));
            }
        }
        return generalists;
    }


    public Patient addPatient(Patient patient) throws Exception {
        System.out.println("Intra aici in backend");
    System.out.println(patient.getCnp());
        if (patientRepository.existsById(patient.getCnp()))
        {
            throw new Exception("Patient exist");

        }
        patient.setRole("PACIENT");
        patient.setPassword(bCryptPasswordEncoder.encode("parola"));
        patientRepository.save(patient);
        System.out.println(patient);
        return patient;
    }
    public List<Patient> allPatient() {
        List<Patient> patientList = new ArrayList<>();
        for(int i=0; i<patientRepository.findAll().size(); i++){
            {
                patientList.add(patientRepository.findAll().get(i));
            }
        }
        return patientList;
    }
    public Hospitalization addHospitalization(String cnpS, String cnpD, String cnpP, Hospitalization hospitalization) throws Exception {
        Date curentData = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        System.out.println(sdf.format(curentData));
        hospitalization.setStartDateHospitalization(sdf.format(curentData));
        if(hospitalizationRepository.existsById(hospitalization.getRegistrationNoHospitalization()))
            throw new Exception("Hospitalization exist");
        String username = secretaryRepository.findByCnp(cnpS).getCnp();
        Secretary secretary = this.secretaryRepository.findByCnp(username);
        hospitalization.setSecretary(secretary);

        if(!patientRepository.existsById(cnpP))
            throw new Exception("Patient doesnt exist");
        Patient patient = this.patientRepository.findByCnp(cnpP);
        hospitalization.setPatient(patient);


        if(!cardiologRepository.existsById(cnpD))
            throw new Exception("You cant assign this doctor because he doesnt exist");
        hospitalization.setCardiolog(cardiologRepository.findByCnp(cnpD));
        hospitalizationRepository.save(hospitalization);
        return hospitalization;
    }
    public Hospitalization editHospitalization(String id) throws ParseException {
        Hospitalization hospitalization = hospitalizationRepository.findByRegistrationNoHospitalization(id);
        Date currentData = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        System.out.println(sdf.format(currentData));
        hospitalization.setEndDateHospitalization(sdf.format(currentData));

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy ", Locale.getDefault());

        System.out.println(formatter.parse(hospitalization.getStartDateHospitalization()));
        System.out.println(formatter.parse(sdf.format(currentData)));
        Date startDate = formatter.parse(hospitalization.getStartDateHospitalization());
        long diff = currentData.getTime() - startDate.getTime();
        System.out.println(diff);
        Integer difference_In_Days
                = Math.toIntExact((diff
                / (1000 * 60 * 60 * 24))
                % 365);
        System.out.println(difference_In_Days+1);
        hospitalization.setNumberOfHospitalization(difference_In_Days+1);
        return hospitalization;
    }
  /*  public String changeHospitalizationDataEnd(String registrationNoHospitalization, Date dateEnd ){

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
    }*/
   /* public String changeHospitalizationNumberOfHospitalization(String registrationNoHospitalization, Integer numberOfHospitalization){
        Optional<Hospitalization> hospitalizationOptional = hospitalizationRepository.findById(registrationNoHospitalization);
        if(!hospitalizationOptional.isPresent()){
            return "This hospitalization doesnt exist";
        }
        Hospitalization hospitalization = hospitalizationRepository.findByRegistrationNoHospitalization(registrationNoHospitalization);
        hospitalization.setNumberOfHospitalization(numberOfHospitalization);
        return "Change Hospitalization Data End";
    }*/
}
