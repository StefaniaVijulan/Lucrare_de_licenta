package com.medicalclinicapp.medicalclinicapp.services;

import com.medicalclinicapp.medicalclinicapp.dto.MailRequest;
import com.medicalclinicapp.medicalclinicapp.models.*;
import com.medicalclinicapp.medicalclinicapp.repository.*;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
import com.medicalclinicapp.medicalclinicapp.security.repository.CardiologRepository;
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
public class PatientService{
    @Autowired
    private EmailService emailService;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private CardiologRepository cardiologRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private AppointmentHematologyRepository appointmentHematologyRepository;
    @Autowired
    private HematologyResultRepository hematologyResultRepository;
    @Autowired
    private RadiologyResultRepository radiologyResultRepository;

    @Autowired
    private AppointmentRadiologyRepository appointmentRadiologyRepository;

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

    public Appointment getNextAppointment(String cnpP) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Bucharest")));

        for(int i=0; i<appointmentRepository.findAll().size(); i++){
            //Luam toate programarile specifice unui pacient
            if(appointmentRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
            {
                Date currentD;
                currentD = new Date();

                System.out.println("current D => " +currentD);
                Date targetDate;
                targetDate = sdf.parse(appointmentRepository.findAll().get(i).getDataA() + " " + appointmentRepository.findAll().get(i).getHour());
                System.out.println(appointmentRepository.findAll().get(i).getDataA() + " " + appointmentRepository.findAll().get(i).getHour());
                boolean stare = targetDate.before(currentD);
                System.out.println("Appointment date" + targetDate);
                System.out.println(stare);
                if (!stare) {
                    return appointmentRepository.findAll().get(i);
                }
            }
        }
        return null;
    }

    public AppointmentHematology getNextAppointmentHematology(String cnpP) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Bucharest")));

        for(int i=0; i<appointmentHematologyRepository.findAll().size(); i++){
            //Luam toate programarile specifice unui pacient
            if(appointmentHematologyRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
            {
                Date currentD;
                currentD = new Date();

                System.out.println("current D => " +currentD);
                Date targetDate;
                targetDate = sdf.parse(appointmentHematologyRepository.findAll().get(i).getDataAppointmentHematology() + " " + appointmentHematologyRepository.findAll().get(i).getHourAppointmentHematology());
                System.out.println(appointmentHematologyRepository.findAll().get(i).getDataAppointmentHematology() + " " + appointmentHematologyRepository.findAll().get(i).getHourAppointmentHematology());
                boolean stare = targetDate.before(currentD);
                System.out.println("Appointment date" + targetDate);
                System.out.println(stare);
                if (!stare) {
                    return appointmentHematologyRepository.findAll().get(i);
                }
            }
        }
        return null;
    }

    public AppointmentRadiology getNextAppointmentRadiology(String cnpP) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Bucharest")));

        for(int i=0; i<appointmentRadiologyRepository.findAll().size(); i++){
            //Luam toate programarile specifice unui pacient
            if(appointmentRadiologyRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
            {
                Date currentD;
                currentD = new Date();

                System.out.println("current D => " +currentD);
                Date targetDate;
                targetDate = sdf.parse(appointmentRadiologyRepository.findAll().get(i).getDataAppointmentRadiology() + " " + appointmentRadiologyRepository.findAll().get(i).getHourAppointmentRadiology());
                System.out.println(appointmentRadiologyRepository.findAll().get(i).getDataAppointmentRadiology() + " " + appointmentRadiologyRepository.findAll().get(i).getHourAppointmentRadiology());
                boolean stare = targetDate.before(currentD);
                System.out.println("Appointment date" + targetDate);
                System.out.println(stare);
                if (!stare) {
                    return appointmentRadiologyRepository.findAll().get(i);
                }
            }
        }
        return null;
    }

    public int addAppointment(String cnpP, String cnpC, Appointment appointment) throws ParseException {
        System.out.println("Add appointment");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for(int i=0; i<appointmentRepository.findAll().size(); i++){
            System.out.println("Intra in verificare daca exista programre viitoare");
            Date currentD;
            currentD = new Date();
            //luam toate programarile viitoare
            // veriricam daca data curenta este inainte datei din progrmare ("<")
            int stare  = currentD.compareTo(sdf.parse(appointmentRepository.findAll().get(i).getDataA()));
            System.out.println("Stare");
            System.out.println(currentD);

            System.out.println(sdf.parse(appointmentRepository.findAll().get(i).getDataA()));
            System.out.println(stare);
            if (stare>0) {
                System.out.println("Intra in stare");
                if(appointmentRepository.findAll().get(i).getCnp().equals(appointment.getCnp())){
                    System.out.println("Exista deja o programare facuta cu acest cnp");
                    //Exista deja o programare facuta pentru acest cnp
                    return 1;
                }
            }
        }

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
        Patient patient;
        patient = patientRepository.findByCnp(cnpP);
        appointment.setLastName(patient.getLastName());
        appointment.setFirstName(patient.getFirstName());
        appointment.setNumberUser(patient.getNumberUser());
        appointment.setEmailUser(patient.getEmailUser());
        appointment.setCnp(cnpP);
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
        //programarea a fost adaugata cu succes
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
        return 0;
    }
    public List<Cardiolog> allCardio(){
        return cardiologRepository.findAll();
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
    public Appointment reprogrameazaAppointment(String cnpP, Appointment appointment) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Bucharest")));

        for(int i=0; i<appointmentRepository.findAll().size(); i++){
            //Luam toate programarile specifice unui pacient
            if(appointmentRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
            {
                Date currentD;
                currentD = new Date();

                System.out.println("current D => " +currentD);
                Date targetDate;
                targetDate = sdf.parse(appointmentRepository.findAll().get(i).getDataA() + " " + appointmentRepository.findAll().get(i).getHour());
                System.out.println(appointmentRepository.findAll().get(i).getDataA() + " " + appointmentRepository.findAll().get(i).getHour());
                boolean stare = targetDate.before(currentD);
                System.out.println("Appointment date" + targetDate);
                System.out.println(stare);
                //Ajungem la urmatoara programare
                if (!stare) {
                    Date newDate = sdf.parse(appointment.getDataA() + " " +appointment.getHour());
                    boolean stare1 = newDate.before(currentD);
                    if(!stare1){
                        appointmentRepository.findAll().get(i).setDataA(appointment.getDataA());
                        appointmentRepository.findAll().get(i).setHour(appointment.getHour());
                        appointmentRepository.save(appointmentRepository.findAll().get(i));
                        //Reprogramarea a fost realizata cu succes
                        return appointmentRepository.findAll().get(i);
                    }


                }
            }
        }
        return null;
    }
    public AppointmentHematology reprogrameazaAppointmentH(String cnpP, AppointmentHematology appointment) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Bucharest")));

        for(int i=0; i<appointmentHematologyRepository.findAll().size(); i++){
            //Luam toate programarile specifice unui pacient
            if(appointmentHematologyRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
            {
                Date currentD;
                currentD = new Date();

                System.out.println("current D => " +currentD);
                Date targetDate;
                targetDate = sdf.parse(appointmentHematologyRepository.findAll().get(i).getDataAppointmentHematology() + " " + appointmentHematologyRepository.findAll().get(i).getHourAppointmentHematology());
                System.out.println(appointmentHematologyRepository.findAll().get(i).getDataAppointmentHematology() + " " + appointmentHematologyRepository.findAll().get(i).getHourAppointmentHematology());
                boolean stare = targetDate.before(currentD);
                System.out.println("Appointment date" + targetDate);
                System.out.println(stare);
                //Ajungem la urmatoara programare
                if (!stare) {
                    Date newDate = sdf.parse(appointment.getDataAppointmentHematology() + " " +appointment.getHourAppointmentHematology());
                    boolean stare1 = newDate.before(currentD);
                    if(!stare1){
                        appointmentHematologyRepository.findAll().get(i).setDataAppointmentHematology(appointment.getDataAppointmentHematology());
                        appointmentHematologyRepository.findAll().get(i).setHourAppointmentHematology(appointment.getHourAppointmentHematology());
                        appointmentHematologyRepository.save(appointmentHematologyRepository.findAll().get(i));
                        //Reprogramarea a fost realizata cu succes
                        return appointmentHematologyRepository.findAll().get(i);
                    }


                }
            }
        }
        return null;
    }
    public AppointmentRadiology reprogrameazaAppointmentR(String cnpP, AppointmentRadiology appointment) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Bucharest")));
        System.out.println("Radioloy appointment => " + appointment);
        for(int i=0; i<appointmentRadiologyRepository.findAll().size(); i++){
            //Luam toate programarile specifice unui pacient
            if(appointmentRadiologyRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
            {
                System.out.println("intra in pacient");

                Date currentD;
                currentD = new Date();


                Date targetDate;
                targetDate = sdf.parse(appointmentRadiologyRepository.findAll().get(i).getDataAppointmentRadiology() + " " + appointmentRadiologyRepository.findAll().get(i).getHourAppointmentRadiology());
                boolean stare = targetDate.before(currentD);
                //Ajungem la urmatoara programare
                if (!stare) {
                    System.out.println("Intra in consultaiile viitoare");
                    Date newDate = sdf.parse(appointment.getDataAppointmentRadiology() + " " +appointment.getHourAppointmentRadiology());
                    boolean stare1 = newDate.before(currentD);
                    if(!stare1){
                        System.out.println("Intra in edit");

                        appointmentRadiologyRepository.findAll().get(i).setDataAppointmentRadiology(appointment.getDataAppointmentRadiology());
                        appointmentRadiologyRepository.findAll().get(i).setHourAppointmentRadiology(appointment.getHourAppointmentRadiology());
                        appointmentRadiologyRepository.save(appointmentRadiologyRepository.findAll().get(i));
                        //Reprogramarea a fost realizata cu succes
                        return appointmentRadiologyRepository.findAll().get(i);
                    }


                }
            }
        }
        return null;
    }

    public List<String> verificaDisponibilitateRadiology() {
        List<AppointmentRadiology> appointmentList = appointmentRadiologyRepository.findAll();
        List<String> newAppointment = new ArrayList<>();
        Collections.sort(appointmentList);
        long count = 1;

        for (int i = 0; i < appointmentList.size() - 1; i++) {
            System.out.println("i= " + i);

            if (appointmentList.get(i).getDataAppointmentRadiology().equals(appointmentList.get(i + 1).getDataAppointmentRadiology())) {
                count += 1;
                System.out.println("\tcount=> " + count);
            } else {
                System.out.println("O luam de la inceput");
                count = 1;
            }
            if (count == 9) {
                System.out.println("Intra aici si face add");
                //Verificam ca daca o anumita data apare de 8 ori (numarul de programari posibile intr-o zi)
                newAppointment.add(appointmentList.get(i).getDataAppointmentRadiology());

            }


        }return newAppointment;
    }
    public List<String> verificaHoursHematology(String dataD) {
        List<AppointmentHematology> appointmentList = appointmentHematologyRepository.findAll();
        List<String> allHours = new ArrayList<>(Arrays.asList("09:00","09:15","09:30","09:45",
                "10:00","10:15","10:30","10:45","11:00","11:15","11:30","11:45",
                "13:00","13:15","13:30","13:45","14:00","14:15","14:30","14:45",
                "15:00","15:15","15:30","15:45","16:00","16:15","16:30","16:45",
                "17:00","17:15","17:30","17:45","18:00"));
        System.out.println("inainte de for" + allHours);
        System.out.println(" list => " +appointmentList.size());
        for (int i = 0; i < appointmentList.size(); i++) {
            System.out.println("intra in for");
            System.out.println(appointmentList.get(i).getDataAppointmentHematology());
            System.out.println(dataD);
            if (appointmentList.get(i).getDataAppointmentHematology().equals(dataD)) {
                String hourD = appointmentList.get(i).getHourAppointmentHematology();
                allHours.remove(hourD);
            }

        }
        System.out.println("dupa for" + allHours);
        return allHours;
    }

    public List<String> verificaDisponibilitateHematology() {
        List<AppointmentHematology> appointmentList = appointmentHematologyRepository.findAll();
        List<String> newAppointment = new ArrayList<>();
        Collections.sort(appointmentList);
        long count = 1;

        for (int i = 0; i < appointmentList.size() - 1; i++) {
            System.out.println("i= " + i);

            if (appointmentList.get(i).getDataAppointmentHematology().equals(appointmentList.get(i + 1).getDataAppointmentHematology())) {
                count += 1;
                System.out.println("\tcount=> " + count);
            } else {
                System.out.println("O luam de la inceput");
                count = 1;
            }
            if (count == 9) {
                System.out.println("Intra aici si face add");
                //Verificam ca daca o anumita data apare de 8 ori (numarul de programari posibile intr-o zi)
                newAppointment.add(appointmentList.get(i).getDataAppointmentHematology());

            }


        }return newAppointment;
    }
    public List<String> verificaHoursRadiology(String dataD) {
        List<AppointmentRadiology> appointmentList = appointmentRadiologyRepository.findAll();
        List<String> allHours = new ArrayList<>(Arrays.asList("09:00","09:15","09:30","09:45",
                "10:00","10:15","10:30","10:45","11:00","11:15","11:30","11:45",
                "13:00","13:15","13:30","13:45","14:00","14:15","14:30","14:45",
                "15:00","15:15","15:30","15:45","16:00","16:15","16:30","16:45",
                "17:00","17:15","17:30","17:45","18:00"));
        System.out.println(allHours);
        for (int i = 0; i < appointmentList.size(); i++) {
            {
                if (appointmentList.get(i).getDataAppointmentRadiology().equals(dataD)) {
                    String hourD = appointmentList.get(i).getDataAppointmentRadiology();
                    allHours.remove(hourD);
                }
            }
        }
        return allHours;
    }
    public List<HematologyResult> allHematologyResult(String cnpP){
        List<HematologyResult> hematologyResultList = new ArrayList<>();
        for(int i=0; i< hematologyResultRepository.findAll().size(); i++){
            if(hematologyResultRepository.findAll().get(i).getAppointmentHematology().getPatient().getCnp().equals(cnpP))
                hematologyResultList.add(hematologyResultRepository.findAll().get(i));
        }
        return hematologyResultList;
    }
    public List<RadiologyResult> allRadiologyResult(String cnpP){
        List<RadiologyResult> radiologyResultsList = new ArrayList<>();
        for(int i=0; i< radiologyResultRepository.findAll().size(); i++){
            if(radiologyResultRepository.findAll().get(i).getAppointmentRadiology().getPatient().getCnp().equals(cnpP))
                radiologyResultsList.add(radiologyResultRepository.findAll().get(i));
        }
        return radiologyResultsList;
    }
}
