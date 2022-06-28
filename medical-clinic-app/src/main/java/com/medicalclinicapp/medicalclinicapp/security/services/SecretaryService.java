package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.models.FisaPatient;
import com.medicalclinicapp.medicalclinicapp.models.Patient;

import com.medicalclinicapp.medicalclinicapp.repository.AppointmentRepository;
import com.medicalclinicapp.medicalclinicapp.repository.FisaPatientRepository;
import com.medicalclinicapp.medicalclinicapp.repository.PatientRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
import com.medicalclinicapp.medicalclinicapp.security.repository.CardiologRepository;
import com.medicalclinicapp.medicalclinicapp.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.time.ZoneId;
import java.util.*;



@Service
@RequiredArgsConstructor
public class SecretaryService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private FisaPatientRepository fisaPatientRepository;


    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CardiologRepository cardiologRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    //programari viitoare
    public List<Appointment> currentAppointments() throws ParseException {
        List<Appointment> appointmentList = new ArrayList<>();
        for (int i = 0; i < appointmentRepository.findAll().size(); i++) {
            {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Bucharest")));

                Date currentD;
                currentD = new Date();
                Date targetDate;
                targetDate = sdf.parse(appointmentRepository.findAll().get(i).getDataA() + " " + appointmentRepository.findAll().get(i).getHour());
                System.out.println(appointmentRepository.findAll().get(i).getDataA() + " " + appointmentRepository.findAll().get(i).getHour());
                boolean stare = targetDate.before(currentD);

                if (!stare) {
                    appointmentList.add(appointmentRepository.findAll().get(i));
                }
            }
        }
        return appointmentList;
    }

    public List<Appointment> todayAppointments() throws ParseException {
        List<Appointment> appointmentList = new ArrayList<>();
        for (int i = 0; i < appointmentRepository.findAll().size(); i++) {
            {


                Date curentData = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                System.out.println("Current");
                System.out.println(sdf.format(curentData));
                System.out.println(appointmentRepository.findAll().get(i).getDataA());
                if (sdf.format(curentData).equals(appointmentRepository.findAll().get(i).getDataA())) {
                    appointmentList.add(appointmentRepository.findAll().get(i));
                }
            }
        }
        Comparator<Appointment> compareByHour =
                (Appointment o1, Appointment o2) -> o1.getHour().compareTo( o2.getHour() );
        Collections.sort(appointmentList, compareByHour);
        return appointmentList;
    }
    public List<Appointment> allAppointment() throws ParseException {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        return appointmentList;
    }

    public Patient addPatient(Patient patient) {
        if (patientRepository.existsByCnp(patient.getCnp())) {
            //pacientul exista
            return null;

        }
        String parola = "parola";
        patient.setPassword(bCryptPasswordEncoder.encode(parola));
       /* String emailtext;
        emailtext = "Buna ziua " + patient.getLastName() + " " + patient.getFirstName() + ",\n\nIti multumim ca ai apelat la serviciile noastre." +
                "Pentru a avea acces la toate informatiile despre investigatiile facute in cadrul acestei clinici te invitam sa accesezi contul creat automat pentru tine. Un nou cont bazat pe CNPul dumneavoastra a fost creat."
                + "\n\nInformatii de conectare:\n\tNumele de utilizator: CNPul dumneavoastra" +
                "\n\tParola: " + parola + "\n\n" +
                "Pentru orice nelamurire va stam la dispozitie.";

        emailService.sendmail(patient.getEmailUser(), "Medical Clinic App - Detalii cont", emailtext);*/
        patient.setRole("PACIENT");
        for (int i = 0; i < appointmentRepository.findAll().size(); i++) {
            {
                if (appointmentRepository.findAll().get(i).getCnp().equals(patient.getCnp()))
                    appointmentRepository.findAll().get(i).setPatient(patient);
            }
        }
        patientRepository.save(patient);
        return patient;
    }

    public Patient checkPatient(String cnp) {
        for (int i = 0; i < patientRepository.findAll().size(); i++) {
            {
                if (patientRepository.findAll().get(i).getCnp().equals(cnp))
                    return patientRepository.findAll().get(i);
            }
        }
        return null;
    }

    public Patient editPatient(String cnpP, Patient patient){
        for(int i=0; i<patientRepository.findAll().size(); i++){
            if(patientRepository.findAll().get(i).getCnp().equals(cnpP)){
                patientRepository.findAll().get(i).setFirstName(patient.getFirstName());
                patientRepository.findAll().get(i).setLastName(patient.getLastName());
                patientRepository.findAll().get(i).setEmailUser(patient.getEmailUser());
                patientRepository.findAll().get(i).setNumberUser(patient.getNumberUser());
                patientRepository.findAll().get(i).setDadLetterPatient(patient.getDadLetterPatient());
                patientRepository.findAll().get(i).setSeriesPatient(patient.getSeriesPatient());
                patientRepository.findAll().get(i).setNumberPatient(patient.getNumberPatient());
                patientRepository.findAll().get(i).setSexPatient(patient.getSexPatient());
                patientRepository.findAll().get(i).setCitizenshipPatient(patient.getCitizenshipPatient());
                patientRepository.findAll().get(i).setCityPatient(patient.getCityPatient());
                patientRepository.findAll().get(i).setTownPatient(patient.getTownPatient());
                patientRepository.findAll().get(i).setStreetPatient(patient.getStreetPatient());
                patientRepository.findAll().get(i).setNoPatient(patient.getNoPatient());
                patientRepository.findAll().get(i).setPlacePatient(patient.getPlacePatient());
                patientRepository.findAll().get(i).setInsurancePatient(patient.getInsurancePatient());
                patientRepository.findAll().get(i).setJobTypePatient(patient.getJobTypePatient());
                patientRepository.save(patientRepository.findAll().get(i));
                break;
            }
        }
        for(int i=0; i<appointmentRepository.findAll().size(); i++){
            if(appointmentRepository.findAll().get(i).getCnp().equals(cnpP)){
                appointmentRepository.findAll().get(i).setFirstName(patient.getFirstName());
                appointmentRepository.findAll().get(i).setLastName(patient.getLastName());
                appointmentRepository.findAll().get(i).setEmailUser(patient.getEmailUser());
                appointmentRepository.findAll().get(i).setNumberUser(patient.getNumberUser());

                appointmentRepository.save(appointmentRepository.findAll().get(i));

            }
        }
        return patient;
    }

    public List<Cardiolog> seeAllCardiolog() {
        List<Cardiolog> generalists = new ArrayList<>();
        for (int i = 0; i < cardiologRepository.findAll().size(); i++) {
            {
                generalists.add(cardiologRepository.findAll().get(i));
            }
        }
        return generalists;
    }
    public Cardiolog getSpecificCardiologOfPatient(String cnp){
        for (int i = 0; i < cardiologRepository.findAll().size(); i++) {
            {
               if(cardiologRepository.findAll().get(i).getCnp().equals(cnp))
                   return cardiologRepository.findAll().get(i);
            }
        }
        return null;
    }
    public FisaPatient addFisa(String cnpP, FisaPatient fisa){
        for (int i = 0; i < patientRepository.findAll().size(); i++) {
            {
                if(patientRepository.findAll().get(i).getCnp().equals(cnpP))
                    fisa.setPatient(patientRepository.findAll().get(i));
            }
        }
        fisaPatientRepository.save(fisa);
        return fisa;
    }

    public Appointment editAppointment(Long id,  String cnpC, Appointment appointment){
        for (int i = 0; i < appointmentRepository.findAll().size(); i++) {
            {
                if (appointmentRepository.findAll().get(i).getId() == id) {
                    Patient patient = appointmentRepository.findAll().get(i).getPatient();
                    appointmentRepository.findAll().get(i).setId(id);
                    appointmentRepository.findAll().get(i).setCnp(appointment.getCnp());
                    appointmentRepository.findAll().get(i).setDataA(appointment.getDataA());
                    appointmentRepository.findAll().get(i).setEmailUser(appointment.getEmailUser());
                    appointmentRepository.findAll().get(i).setNumberUser(appointment.getNumberUser());
                    appointmentRepository.findAll().get(i).setHour(appointment.getHour());
                    appointmentRepository.findAll().get(i).setFirstName(appointment.getFirstName());
                    appointmentRepository.findAll().get(i).setLastName(appointment.getLastName());
                    appointmentRepository.findAll().get(i).setCardiolog(cardiologRepository.findByCnp(cnpC));
                    appointmentRepository.findAll().get(i).setPatient(patient);
                    appointmentRepository.save(appointmentRepository.findAll().get(i));
                }
            }
        }
        return appointment;
    }

    public Appointment deleteAppointment(Long id){
        for(int i= 0; i<appointmentRepository.findAll().size(); i++){
            if(appointmentRepository.findAll().get(i).getId() == id){
                Appointment appointment = appointmentRepository.findAll().get(i);
                appointmentRepository.delete(appointmentRepository.findAll().get(i));
                return appointment;
            }
        }
        return null;
    }

    public Patient moreInfo(String cnpP){
        for(int i=0; i<patientRepository.findAll().size(); i++){
            if(patientRepository.findAll().get(i).getCnp().equals(cnpP)){
                return patientRepository.findAll().get(i);
            }
        }
        return null;
    }
}
