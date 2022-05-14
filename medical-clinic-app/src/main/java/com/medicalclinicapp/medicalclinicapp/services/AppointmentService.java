package com.medicalclinicapp.medicalclinicapp.services;

import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentRepository;
import com.medicalclinicapp.medicalclinicapp.repository.PatientRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
import com.medicalclinicapp.medicalclinicapp.security.repository.CardiologRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.apache.poi.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private CardiologRepository cardiologRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Cardiolog> seeAllCardiolog() {
        List<Cardiolog> generalists = new ArrayList<>();
        for (int i = 0; i < cardiologRepository.findAll().size(); i++) {
            {
                generalists.add(cardiologRepository.findAll().get(i));
            }
        }
        return generalists;
    }
    public Patient getSpecificPatient(String cnpP){
        for (int i = 0; i < patientRepository.findAll().size(); i++) {
            if(patientRepository.findAll().get(i).getCnp().equals(cnpP))
                return patientRepository.findAll().get(i);
        }
        return null;
    }

    public List<String> verificaDisponibilitateDoctor(String cnpD){
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<String> newAppointment = new ArrayList<>();
        Collections.sort(appointmentList);
        long count = 0;
        for (int i = 0; i < appointmentList.size() - 1; i++) {
            if(appointmentList.get(i).getCardiolog().getCnp().equals(cnpD))
                //incepem sa calculam de  cate ori apare o zi in appointment-urile curente
                if (appointmentList.get(i).getDataA().equals(appointmentList.get(i + 1).getDataA()))
                    count += 1;
                else {
                    count += 1;
                    if (count == 8) {
                //Verificam ca daca o anumita data apare de 8 ori (numarul de programari posibile intr-o zi)
                        newAppointment.add(appointmentList.get(i).getDataA());
                    } else {
                        count = 0;
                    }
                }
        }

        return newAppointment;
    }
    public List<String> verificaHoursDoctor(String cnpC, String dataD) {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<String> allHours = new ArrayList<>(Arrays.asList("09:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00", "18:00"));
        System.out.println(appointmentList);
        for (int i = 0; i < appointmentList.size(); i++) {
            {
                System.out.println(appointmentList.get(i).getCardiolog());
                System.out.println(appointmentList.get(i).getCardiolog().getCnp());
                if(appointmentList.get(i).getCardiolog().getCnp().equals(cnpC))
                    if (appointmentList.get(i).getDataA().equals(dataD)) {

                        String hourD = appointmentList.get(i).getHour();

                        allHours.remove(hourD);
                    }
            }
        }
        System.out.println("all hours");
        System.out.println(allHours);
        return allHours;
    }

    public Appointment addAppointment(String cnpC, Appointment appointment) {
        System.out.println("Add appointment");
        String emailtext;
        emailtext = "Buna ziua, \n" +
                "Va multumim ca ati apelat la serviciile noastre. Va asteptam pe data de " + appointment.getDataA() + ", la ora " + appointment.getHour() + "." +
                "\n\nPentru orice alta informatie nu ezitati sa ne contactati la numarul de telefon: 0760774768. ";
        emailService.sendmail(appointment.getEmailUser(), "Medical Clinic App - Detalii programare", emailtext);
       // Patient patient = new Patient();
        for (int i = 0; i < patientRepository.findAll().size(); i++) {
            if(patientRepository.findAll().get(i).getCnp().equals(appointment.getCnp()))
            {   System.out.println(patientRepository.findAll().get(i));
                appointment.setPatient(patientRepository.findAll().get(i));

            }
        }
        for (int i = 0; i < cardiologRepository.findAll().size(); i++) {
            if(cardiologRepository.findAll().get(i).getCnp().equals(cnpC))
            {
                System.out.println(cardiologRepository.findAll().get(i).getCnp());
                System.out.println(cardiologRepository.findAll().get(i).getFirstName());
                appointment.setCardiolog(cardiologRepository.findAll().get(i));
                break;
            }
        }
        appointmentRepository.save(appointment);
        return appointment;
    }

    public Appointment deleteAppointment(Long idA) {
        for (int i = 0; i < appointmentRepository.findAll().size(); i++) {
            if (appointmentRepository.findAll().get(i).getId().equals(idA)) {
                appointmentRepository.delete(appointmentRepository.findAll().get(i));
            }
        }
        return null;
    }
}