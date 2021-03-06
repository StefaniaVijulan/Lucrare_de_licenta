package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.dto.MailRequest;
import com.medicalclinicapp.medicalclinicapp.models.*;

import com.medicalclinicapp.medicalclinicapp.repository.*;
import com.medicalclinicapp.medicalclinicapp.security.models.*;
import com.medicalclinicapp.medicalclinicapp.security.repository.CardiologRepository;
import com.medicalclinicapp.medicalclinicapp.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.StandardSocketOptions;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CardiologService {
    @Autowired
    private EmailService emailService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentHematologyRepository appointmentHematologyRepository;

    @Autowired
    private AppointmentRadiologyRepository appointmentRadiologyRepository;

    @Autowired
    private HematologyResultRepository hematologyResultRepository;

    @Autowired
    private FisaPatientRepository fisaPatientRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private CardiologRepository cardiologRepository;


    public List<Appointment> getAllSpecificAppointment(String cnp){


        Date curentData = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        List<Appointment> appointmentList = new ArrayList<>();
        for(int i=0; i<appointmentRepository.findAll().size(); i++){
            if(appointmentRepository.findAll().get(i).getCardiolog().getCnp().equals(cnp))
                {
                    appointmentList.add(appointmentRepository.findAll().get(i));
                }
        }
        Comparator<Appointment> compareByHour =
                (Appointment o1, Appointment o2) -> o1.getHour().compareTo( o2.getHour() );
        Collections.sort(appointmentList, compareByHour);

        return appointmentList;
    }
    public List<Appointment> getAllFutureAppointment(String cnp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Europe/Bucharest")));

        List<Appointment> appointmentList = new ArrayList<>();
        for(int i=0; i<appointmentRepository.findAll().size(); i++){
            if(appointmentRepository.findAll().get(i).getCardiolog().getCnp().equals(cnp))
            {
                Date currentD;
                currentD = new Date();
                System.out.println("current D => " +currentD);
                Date targetDate;
                targetDate = sdf.parse(appointmentRepository.findAll().get(i).getDataA() + " " + appointmentRepository.findAll().get(i).getHour());
                boolean stare = targetDate.before(currentD);

                System.out.println("Appointment date" + sdf.parse(appointmentRepository.findAll().get(i).getDataA()));
                System.out.println(stare);
                if (!stare) {
                    appointmentList.add(appointmentRepository.findAll().get(i));
                }
            }
        }
        Comparator<Appointment> compareByHour =
                (Appointment o1, Appointment o2) -> o1.getHour().compareTo( o2.getHour() );
        Collections.sort(appointmentList, compareByHour);

        return appointmentList;
    }
    public List<Appointment> todaySpecificAppointment(String cnp){

        Date curentData = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        List<Appointment> appointmentList = new ArrayList<>();
        for(int i=0; i<appointmentRepository.findAll().size(); i++){
            if(appointmentRepository.findAll().get(i).getCardiolog().getCnp().equals(cnp))
                if (sdf.format(curentData).equals(appointmentRepository.findAll().get(i).getDataA())) {
                    appointmentList.add(appointmentRepository.findAll().get(i));
                }
        }
        Comparator<Appointment> compareByHour =
                (Appointment o1, Appointment o2) -> o1.getHour().compareTo( o2.getHour() );
        Collections.sort(appointmentList, compareByHour);

        return appointmentList;
    }

    public FisaPatient editFisaPatient(String cnpP, FisaPatient fisaPatient){

        for(int i=0; i<fisaPatientRepository.findAll().size(); i++){
            System.out.println("i => "+i);
            if(fisaPatientRepository.findAll().get(i).getPatient().getCnp().equals(cnpP)){
                System.out.println("Intra aici fisa");
                fisaPatientRepository.findAll().get(i).setBloodTypePatient(fisaPatient.getBloodTypePatient());
                fisaPatientRepository.findAll().get(i).setRhPatient(fisaPatient.getRhPatient());
                fisaPatientRepository.findAll().get(i).setAllergyPatient(fisaPatient.getAllergyPatient());

                fisaPatientRepository.findAll().get(i).setFamilyHistory(fisaPatient.getFamilyHistory());
                fisaPatientRepository.findAll().get(i).setPersonalHistory(fisaPatient.getPersonalHistory());
                fisaPatientRepository.findAll().get(i).setLifeAndWorkConditional(fisaPatient.getLifeAndWorkConditional());
                fisaPatientRepository.findAll().get(i).setBehavior(fisaPatient.getBehavior());
                fisaPatientRepository.findAll().get(i).setPillsHistory(fisaPatient.getPillsHistory());

                //Examen obiectiv
                fisaPatientRepository.findAll().get(i).setGeneralCondition(fisaPatient.getGeneralCondition());
                fisaPatientRepository.findAll().get(i).setWaist(fisaPatient.getWaist());
                fisaPatientRepository.findAll().get(i).setWeight(fisaPatient.getWeight());
                fisaPatientRepository.findAll().get(i).setNutritionalStatus(fisaPatient.getNutritionalStatus());
                fisaPatientRepository.findAll().get(i).setGanglionSystem(fisaPatient.getGanglionSystem());
                fisaPatientRepository.findAll().get(i).setConnectiveTissue(fisaPatient.getConnectiveTissue());

                fisaPatientRepository.findAll().get(i).setCardiovascularSystem(fisaPatient.getCardiovascularSystem());

                fisaPatientRepository.save(fisaPatientRepository.findAll().get(i));
                System.out.println("Fisa patient a");
                System.out.println(fisaPatientRepository.findAll().get(i));
                break;
            }
        }

        return fisaPatient;


    }
    public FisaPatient delaleteFisa(Long blood){
        for(int i=0; i<fisaPatientRepository.findAll().size(); i++) {
            if (fisaPatientRepository.findAll().get(i).getNoFile().equals(blood))
                fisaPatientRepository.deleteById(blood);
        }
        return null;
    }
    public FisaPatient getInfoFisa (String cnpP){
        for(int i=0; i<fisaPatientRepository.findAll().size(); i++){
            if(fisaPatientRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
                return fisaPatientRepository.findAll().get(i);
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

    public Boolean verficaDateBeforeBlock(String dat, String cnpC){
        List<Appointment> appointmentList = appointmentRepository.findAll();
        System.out.println("Intra in check appoint service");
        Collections.sort(appointmentList);
        System.out.println("APPOINTMNET LIST=>" + appointmentList);
        long count =0;
        for(int i=0; i<appointmentRepository.findAll().size(); i++){
            System.out.println("Intra aici in for");
            System.out.println(cnpC);
            System.out.println(appointmentRepository.findAll().get(i).getCardiolog().getCnp());
            if(appointmentRepository.findAll().get(i).getCardiolog().getCnp().equals(cnpC)){
                System.out.println(appointmentRepository.findAll().get(i).getCardiolog().getCnp());
                System.out.println(appointmentRepository.findAll().get(i).getDataA());

                if(appointmentRepository.findAll().get(i).getDataA().equals(dat)){
                        count +=1;
                }

            }

        }
        if(count == 0)
            return true;
        return false;
    }

    public AppointmentHematology addAppointmentHematology(String cnpP, AppointmentHematology appointment) throws Exception {
        for (int i = 0; i < patientRepository.findAll().size(); i++) {
            if (patientRepository.findAll().get(i).getCnp().equals(cnpP)) {
                appointment.setPatient(patientRepository.findAll().get(i));
                break;
            }
        }
            appointmentHematologyRepository.save(appointment);
            return appointment;
    }
    public AppointmentHematology getSpecificAppointmentHematology(String cnpP) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        for(int i=0; i<appointmentHematologyRepository.findAll().size(); i++){
            if(appointmentHematologyRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
            {
                Date curentData = new Date();

                int dataC;

                dataC = sdf.format(curentData).compareTo(appointmentHematologyRepository.findAll().get(i).getDataAppointmentHematology());
                if(dataC <= 0 ){

                    return appointmentHematologyRepository.findAll().get(i);
                }
            }
        }
        return  null;
    }

    public AppointmentRadiology addAppointmentRadiology(String cnpP, AppointmentRadiology appointment) throws Exception {
        for (int i = 0; i < patientRepository.findAll().size(); i++) {
            if (patientRepository.findAll().get(i).getCnp().equals(cnpP)) {
                appointment.setPatient(patientRepository.findAll().get(i));
                break;
            }
        }
        appointmentRadiologyRepository.save(appointment);
        return appointment;

    }
    public AppointmentRadiology getSpecificAppointmentRadiology(String cnpP) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        for(int i=0; i<appointmentRadiologyRepository.findAll().size(); i++){
            if(appointmentRadiologyRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
            {{
                Date curentData = new Date();

                int dataC;
                dataC = sdf.format(curentData).compareTo(appointmentRadiologyRepository.findAll().get(i).getDataAppointmentRadiology());
                if(dataC <= 0 ){

                    return appointmentRadiologyRepository.findAll().get(i);
                }
            }
            }
        }
        return  null;
    }

    public String deltete(){
        for (int i = 0; i < appointmentHematologyRepository.findAll().size(); i++) {

                if (appointmentHematologyRepository.findAll().get(i).getDataAppointmentHematology() == null){
                    appointmentHematologyRepository.delete(appointmentHematologyRepository.findAll().get(i));
                }

        }
        return "done";
            };
    public AppointmentHematology getPatientAppointmentsHematology(String cnpP){
        for(int i=0; i<appointmentHematologyRepository.findAll().size(); i++){
            if(appointmentHematologyRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
            {

                Date curentData = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                int dataC;
                dataC = sdf.format(curentData).compareTo(appointmentHematologyRepository.findAll().get(i).getDataAppointmentHematology());
                if(dataC < 0 || dataC == 0 )
                    return appointmentHematologyRepository.findAll().get(i);
            }
        }
        return null;
    }
    public AppointmentRadiology getPatientAppointmentsRadiology(String cnpP){
        for(int i=0; i<appointmentRadiologyRepository.findAll().size(); i++){
            if(appointmentRadiologyRepository.findAll().get(i).getPatient().getCnp().equals(cnpP))
            {
                return appointmentRadiologyRepository.findAll().get(i);
            }
        }
        return null;
    }
    public Appointment editAppointment(Long id,  Appointment appointment){
        for (int i = 0; i < appointmentRepository.findAll().size(); i++) {
            {
                if (appointmentRepository.findAll().get(i).getId() == id) {
                    Patient patient = appointmentRepository.findAll().get(i).getPatient();
                    Cardiolog cardiolog = appointmentRepository.findAll().get(i).getCardiolog();
                    appointmentRepository.findAll().get(i).setId(id);
                    appointmentRepository.findAll().get(i).setCnp(appointment.getCnp());
                    appointmentRepository.findAll().get(i).setDataA(appointment.getDataA());
                    appointmentRepository.findAll().get(i).setEmailUser(appointment.getEmailUser());
                    appointmentRepository.findAll().get(i).setNumberUser(appointment.getNumberUser());
                    appointmentRepository.findAll().get(i).setHour(appointment.getHour());
                    appointmentRepository.findAll().get(i).setFirstName(appointment.getFirstName());
                    appointmentRepository.findAll().get(i).setLastName(appointment.getLastName());
                    appointmentRepository.findAll().get(i).setCardiolog(cardiolog);
                    appointmentRepository.findAll().get(i).setPatient(patient);
                    appointmentRepository.save(appointmentRepository.findAll().get(i));
                }
            }
        }
        return appointment;
    }

    public List<HematologyResult> seeHematologyResult(String cnpP){
        List<HematologyResult> hematologyResultList = new ArrayList<>();
        for(int i=0; i<hematologyResultRepository.findAll().size(); i++){
            if(hematologyResultRepository.findAll().get(i).getDone() != null){
                if(hematologyResultRepository.findAll().get(i).getAppointmentHematology().getPatient().getCnp().equals(cnpP)){
                    hematologyResultList.add(hematologyResultRepository.findAll().get(i));
                }
            }
        }
        return hematologyResultList;
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
        System.out.println("0");
        return 0;
    }
}


