package com.medicalclinicapp.medicalclinicapp.services;

import com.medicalclinicapp.medicalclinicapp.dto.MailRequest;
import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentRepository;
import com.medicalclinicapp.medicalclinicapp.repository.PatientRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
import com.medicalclinicapp.medicalclinicapp.security.repository.CardiologRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        for(int i=0; i<appointmentRepository.findAll().size(); i++){
            //Luam toate programarile specifice unui pacient
            if(appointmentRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
            {
                Date currentD;
                currentD = new Date();
                System.out.println("current D => " +currentD);
                boolean stare = (sdf.parse(appointmentRepository.findAll().get(i).getDataA())).before(currentD);
                System.out.println("Appointment date" + sdf.parse(appointmentRepository.findAll().get(i).getDataA()));
                System.out.println(stare);
                if (!stare) {
                    return appointmentRepository.findAll().get(i);
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
        Patient patient = new Patient();
        patient = patientRepository.findByCnp(cnpP);
        appointment.setLastName(patient.getLastName());
        appointment.setFirstName(patient.getFirstName());
        appointment.setNumberUser(patient.getNumberUser());
        appointment.setEmailUser(patient.getEmailUser());
        appointment.setCnp(cnpP);
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
}
