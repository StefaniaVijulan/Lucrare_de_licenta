package com.medicalclinicapp.medicalclinicapp.services;

import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.apache.poi.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;


    @Autowired
    private EmailService emailService;

    public List<String> verificaDisponibilitate() {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<String> newAppointment = new ArrayList<>();
        Collections.sort(appointmentList);
        System.out.println("Appointment list =>");
        System.out.println(appointmentList);
        long count = 0;
        for (int i = 0; i < appointmentList.size() - 1; i++) {
            if (appointmentList.get(i).getDataA().equals(appointmentList.get(i + 1).getDataA()))
                count += 1;
            else {
                count += 1;
                if (count == 8) {

                    newAppointment.add(appointmentList.get(i).getDataA());
                } else {
                    count = 0;
                }
            }
        }
        System.out.println("Data block");
        System.out.println(newAppointment);
        return newAppointment;
    }

    public List<String> verificaHours(String dataD) {
        System.out.println("intra aici");
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<String> allHours = new ArrayList<>(Arrays.asList("09:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00", "17:00", "18:00"));

        for (int i = 0; i < appointmentList.size(); i++) {
            {

                System.out.println(appointmentList.get(i).getDataA());
                System.out.println(dataD);
                if (appointmentList.get(i).getDataA().equals(dataD)) {

                    String hourD = appointmentList.get(i).getHour();
                    System.out.println("Hour block =>");
                    System.out.println(hourD);
                    System.out.println(allHours);
                    allHours.remove(hourD);
                }
            }
        }
        System.out.println("all hours");
        System.out.println(allHours);
        return allHours;
    }

    public Appointment addAppointment(Appointment appointment) {
        String emailtext;
        emailtext = "Buna ziua, \n" +
                "Va multumim ca ati apelat la serviciile noastre. Va asteptam pe data de " + appointment.getDataA() + ", la ora " + appointment.getHour() + "." +
                "\n\nPentru orice alta informatie nu ezitati sa ne contactati la numarul de telefon: 0760774768. ";
        emailService.sendmail(appointment.getEmailUser(), "Medical Clinic App - Detalii programare", emailtext);
        return appointmentRepository.save(appointment);
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