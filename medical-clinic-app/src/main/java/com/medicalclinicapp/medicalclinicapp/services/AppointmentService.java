package com.medicalclinicapp.medicalclinicapp.services;

import com.medicalclinicapp.medicalclinicapp.dto.MailRequest;
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

import java.net.StandardSocketOptions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
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

    public List<String> verificaDisponibilitateDoctor(String cnpD) {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<String> newAppointment = new ArrayList<>();
        Collections.sort(appointmentList);
        long count = 1;
        System.out.println("cnpD => " + cnpD);

        for (int i = 0; i < appointmentList.size() - 1; i++) {
            System.out.println("i= " + i);
            if (appointmentList.get(i).getCardiolog().getCnp().equals(cnpD)) {
                System.out.println("Appointment" + appointmentList.get(i).getId());
                System.out.println("\tappointmentList.get(i).getDataA()" + appointmentList.get(i).getDataA());
                System.out.println("\tappointmentList.get(i + 1).getDataA()" + appointmentList.get(i + 1).getDataA());

                //incepem sa calculam de  cate ori apare o zi in appointment-urile curente
                if (appointmentList.get(i).getDataA().equals(appointmentList.get(i + 1).getDataA())) {
                    count += 1;
                    System.out.println("\tcount=> " + count);
                } else {
                    System.out.println("O luam de la inceput");
                    count = 1;
                }

            }
            if (count == 9) {
                System.out.println("Intra aici si face add");
                //Verificam ca daca o anumita data apare de 8 ori (numarul de programari posibile intr-o zi)
                newAppointment.add(appointmentList.get(i).getDataA());

            }


        }return newAppointment;
    }
    public List<String> verificaHoursDoctor(String cnpC, String dataD) {
        List<Appointment> appointmentList = appointmentRepository.findAll();
        List<String> allHours = new ArrayList<>(Arrays.asList("09:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"));
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

    public int addAppointment(String cnpC, Appointment appointment) throws ParseException {
        System.out.println("Add appointment");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Bucharest")));
        for(int i=0; i<appointmentRepository.findAll().size(); i++){
            Date currentD;
            currentD = new Date();
            Date targetDate;
            targetDate = sdf.parse(appointmentRepository.findAll().get(i).getDataA() + " " + appointmentRepository.findAll().get(i).getHour());
            //luam toate programarile viitoare
            // veriricam daca data curenta este inainte datei din progrmare ("<")
            boolean stare = targetDate.before(currentD);
            System.out.println("Stare");
            System.out.println(currentD);

            System.out.println(sdf.parse(appointmentRepository.findAll().get(i).getDataA()));
            System.out.println(stare);
            if (!stare) {
                System.out.println("Intra in stare");
                if(appointmentRepository.findAll().get(i).getCnp().equals(appointment.getCnp())){
                    System.out.println("Exista deja o programare facuta cu acest cnp");
                    //Exista deja o programare facuta pentru acest cnp
                    return 1;
                }
            }
        }
        for(int i=0; i<patientRepository.findAll().size(); i++){
                if(patientRepository.findAll().get(i).getCnp().equals(appointment.getCnp())){
                    //Exista deja un cont creat pentru acest cnp
                    System.out.println("Exista un cont");
                    return 2;
                }
            }
        String emailtext;
        emailtext = "Buna ziua, \n" +
                "Va multumim ca ati apelat la serviciile noastre. Va asteptam pe data de " + appointment.getDataA() + ", la ora " + appointment.getHour() + "." +
                "\n\nPentru orice alta informatie nu ezitati sa ne contactati la numarul de telefon: 0760774768. ";
     //   emailService.sendmail(appointment.getEmailUser(), "Medical Clinic App - Detalii programare", emailtext);*/
       // Patient patient = new Patient();
        MailRequest mailRequest = new MailRequest();
        mailRequest.setTo(appointment.getEmailUser());
        mailRequest.setSubject("Your Heart Clinic - Programare consult");
        Map<String, Object> model = new HashMap<>();
        model.put("action","Programare consult");
        model.put("body", emailtext);
        emailService.sendmail(mailRequest, model);
        for (int i = 0; i < patientRepository.findAll().size(); i++) {
            if(patientRepository.findAll().get(i).getCnp().equals(appointment.getCnp()))
            {   System.out.println(patientRepository.findAll().get(i));
                appointment.setPatient(patientRepository.findAll().get(i));

            }
        }
        for (int i = 0; i < cardiologRepository.findAll().size(); i++) {
            if(cardiologRepository.findAll().get(i).getCnp().equals(cnpC))
            {
                appointment.setCardiolog(cardiologRepository.findAll().get(i));
                break;
            }
        }
        appointmentRepository.save(appointment);
        //programarea a fost adaugata cu succes
        return 0;
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