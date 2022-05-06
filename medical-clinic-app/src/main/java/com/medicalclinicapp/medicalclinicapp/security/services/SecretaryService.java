package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.models.Patient;

import com.medicalclinicapp.medicalclinicapp.repository.HospitalizationRepository;
import com.medicalclinicapp.medicalclinicapp.repository.PatientRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import com.medicalclinicapp.medicalclinicapp.security.models.User;
import com.medicalclinicapp.medicalclinicapp.security.repository.CardiologRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.SecretaryRepository;
import com.medicalclinicapp.medicalclinicapp.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private PatientRepository patientRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private HospitalizationRepository hospitalizationRepository;

    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private CardiologRepository cardiologRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Patient addPatient(Patient patient) throws Exception {
        if (patientRepository.existsByCnp(patient.getCnp()))
        {
            //pacientul exista
            return null;

        }
        String parola = "parola";
        patient.setPassword(bCryptPasswordEncoder.encode(parola));
        String emailtext;
        emailtext = "Buna ziua " + patient.getLastName() + " " + patient.getFirstName() +",\n\nIti multumim ca ai apelat la serviciile noastre." +
                "Pentru a avea acces la toate informatiile despre investigatiile facute in cadrul acestei clinici te invitam sa accesezi contul creat automat pentru tine. Un nou cont bazat pe CNPul dumneavoastra a fost creat."
                +"\n\nInformatii de conectare:\n\tNumele de utilizator: CNPul dumneavoastra" +
                "\n\tParola: " + parola +"\n\n" +
                "Pentru orice nelamurire va stam la dispozitie.";

        emailService.sendmail(patient.getEmailUser(),"Medical Clinic App - Detalii cont",emailtext);
        patient.setRole("PACIENT");
        patientRepository.save(patient);
        return patient;
    }

    public Hospitalization addHospitalization(String cnpS, String cnpC, String cnpP, Hospitalization hospitalization){
        Date curentData = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        hospitalization.setStartDateHospitalization(sdf.format(curentData));

        if(hospitalizationRepository.existsById(hospitalization.getRegistrationNoHospitalization()))
            //Numarul de inregistrare a acestei internari este deja luat
            return null;


        Secretary secretary = this.secretaryRepository.findByCnp(cnpS);
        System.out.println("Secretar");
        System.out.println(cnpS);
        System.out.println(secretary);
        hospitalization.setSecretary(secretary);

        Patient patient = this.patientRepository.findByCnp(cnpP);
        System.out.println("Patient");
        System.out.println((cnpP));
        System.out.println(patient);
        hospitalization.setPatient(patient);

        Cardiolog cardiolog = this.cardiologRepository.findByCnp(cnpC);
        System.out.println("Cardiolog");
        System.out.println(cardiolog);
        hospitalization.setCardiolog(cardiolog);

        hospitalizationRepository.save(hospitalization);

        return hospitalization;
    }

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
    public List<Cardiolog> seeAllCardiolog(){
        List<Cardiolog> generalists = new ArrayList<>();
        for(int i=0; i<cardiologRepository.findAll().size(); i++){
            {
                generalists.add(cardiologRepository.findAll().get(i));
            }
        }
        return generalists;
    }

    // Date despre internarea specifica unui pacient
    public Hospitalization getSpecificHospitalization(String noHosp){
        Hospitalization hospitalization= new Hospitalization();
        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            {
                if(hospitalizationRepository.findAll().get(i).getRegistrationNoHospitalization().equals(noHosp))
                    hospitalization = hospitalizationRepository.findAll().get(i);
            }
        }
        return hospitalization;
    }
}
 /*

    public Hospitalization newHospitalization(String registrationNo, Patient patient){
        System.out.println("newHospitalization");
        System.out.println("\tAdaugam un pacient nou");
        if (patientRepository.existsById(patient.getCnp()))
        {
            return null;

        }

        System.out.println("\tAdaugam internare");
        Hospitalization hospitalization = new Hospitalization();

        hospitalization.setRegistrationNoHospitalization(registrationNo);

        Date curentData = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
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
        return patient;
    }*/
 /*   //internarile active


    public Patient getSpecificPatient(String registrationNoHospitalization) {
        Patient patient = new Patient();

        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            {
                if(hospitalizationRepository.findAll().get(i).getRegistrationNoHospitalization().equals(registrationNoHospitalization))
                    patient = hospitalizationRepository.findAll().get(i).getPatient();
            }
        }
        return patient;
    }

    // Date despre internarea specifica unui pacient
    public Hospitalization getSpecificHospitalizationofPatient(String cnpP){
        Hospitalization hospitalization= new Hospitalization();
        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            {
                if(hospitalizationRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
                    hospitalization = hospitalizationRepository.findAll().get(i);
            }
        }
        return hospitalization;
    }

    // Date despre doctorul specifica unui pacient internat
    public Cardiolog getSpecificCardiologOfPatient(String cnp){
        Cardiolog cardiolog= new Cardiolog();
        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            {
                if(hospitalizationRepository.findAll().get(i).getPatient().getCnp().equals(cnp))
                    cardiolog = hospitalizationRepository.findAll().get(i).getCardiolog();
            }
        }
        return cardiolog;
    }

    public List<Hospitalization> getHospitalizationBetweenData(String dateStart, String dataEnd){
        List<Hospitalization> hospitalizationList = new ArrayList<>();
        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            {
                if(hospitalizationRepository.findAll().get(i).getEndDateHospitalization() == null)
                    hospitalizationList.add(hospitalizationRepository.findAll().get(i));
            }
        }
        return hospitalizationList;
    }

    public Patient moreInfo(String cnp){
        System.out.println("Inainte de for");
        System.out.println(cnp);
        Patient patient = new Patient();
        for(int i=0; i<patientRepository.findAll().size(); i++){
            {   System.out.println("in for");
                System.out.println(cnp);
                System.out.println(patientRepository.findAll().get(i).getCnp());
                if(patientRepository.findAll().get(i).getCnp().equals(cnp))
                {
                    System.out.println("in if");
                    System.out.println(patientRepository.findAll().get(i).getCnp());
                    System.out.println(patientRepository.findAll().get(i));
                    patient = patientRepository.findAll().get(i);
                }
            }
        }
        System.out.println(patient);
        return patient;
    }
    public Hospitalization moreInfoHospitalization(String cnp){
        for(int i=0; i<hospitalizationRepository.findAll().size(); i++){
            if(hospitalizationRepository.findAll().get(i).getPatient().getCnp().equals(cnp));
            {
                return hospitalizationRepository.findAll().get(i);
            }
        }
        return null;
    }


    public ResponseEntity<Patient> afisareP(String cnpP){
        if (!patientRepository.existsById(cnpP))
        {
            return null;
        } else {
            Patient patient = patientRepository.findByCnp(cnpP);
            return new ResponseEntity<>(patient, HttpStatus.OK);


        }
    }

    public Patient deletePatient(String cnpP){
        if (!patientRepository.existsById(cnpP))
        {
            return null;
        }
        else{
            patientRepository.delete(patientRepository.findByCnp(cnpP));
            return patientRepository.findByCnp(cnpP);
        }

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
        hospitalizationRepository.save(hospitalization);
        return hospitalization;
    }*/
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
    }
}
        */