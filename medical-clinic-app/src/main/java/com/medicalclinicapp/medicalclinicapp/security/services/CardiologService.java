package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.*;

import com.medicalclinicapp.medicalclinicapp.repository.*;
import com.medicalclinicapp.medicalclinicapp.security.models.*;
import com.medicalclinicapp.medicalclinicapp.security.repository.CardiologRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CardiologService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentHematologyRepository appointmentHematologyRepository;

    @Autowired
    private AppointmentRadiologyRepository appointmentRadiologyRepository;

    @Autowired
    private FisaPatientRepository fisaPatientRepository;

    @Autowired
    private PatientRepository patientRepository;


    public List<Appointment> getAllSpecificAppointment(String cnp){


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

    public FisaPatient delaleteFisa(Long blood)
    {
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

    public List<String> verificaDisponibilitateHematology(){
        List<AppointmentHematology> appointmentList = appointmentHematologyRepository.findAll();
        List<String> newAppointment = new ArrayList<>();
        Collections.sort(appointmentList);
        long count = 0;
        for (int i = 0; i < appointmentList.size() - 1; i++) {
                //incepem sa calculam de  cate ori apare o zi in appointment-urile curente
                if (appointmentList.get(i).getDataAppointmentHematology().equals(appointmentList.get(i + 1).getDataAppointmentHematology()))
                    count += 1;
                else {
                    count += 1;
                    if (count == 8) {
                        //Verificam ca daca o anumita data apare de 8 ori (numarul de programari posibile intr-o zi)
                        newAppointment.add(appointmentList.get(i).getDataAppointmentHematology());
                    } else {
                        count = 0;
                    }
                }
        }

        return newAppointment;
    }
    public List<String> verificaHoursHematology(String dataD) {
        List<AppointmentHematology> appointmentList = appointmentHematologyRepository.findAll();
        List<String> allHours = new ArrayList<>(Arrays.asList("09:00","09:15","09:30","09:45",
                "10:00","10:15","10:30","10:45","11:00","11:15","11:30","11:45",
                "12:00","12:15","12:30","12:45","14:00","14:15","14:30","14:45",
                "15:00","15:15","15:30","15:45","16:00","16:15","16:30","16:45",
                "17:00","17:15","17:30","17:45","18:00"));
        System.out.println(allHours);
        for (int i = 0; i < appointmentList.size(); i++) {
            {
                if (appointmentList.get(i).getDataAppointmentHematology().equals(dataD)) {
                    String hourD = appointmentList.get(i).getHourAppointmentHematology();
                    allHours.remove(hourD);
                }
            }
        }
        return allHours;
    }

    public List<String> verificaDisponibilitateRadiology(){
        List<AppointmentRadiology> appointmentList = appointmentRadiologyRepository.findAll();
        List<String> newAppointment = new ArrayList<>();
        Collections.sort(appointmentList);
        long count = 0;
        for (int i = 0; i < appointmentList.size() - 1; i++) {
            //incepem sa calculam de  cate ori apare o zi in appointment-urile curente
            if (appointmentList.get(i).getDataAppointmentRadiology().equals(appointmentList.get(i + 1).getDataAppointmentRadiology()))
                count += 1;
            else {
                count += 1;
                if (count == 8) {
                    //Verificam ca daca o anumita data apare de 8 ori (numarul de programari posibile intr-o zi)
                    newAppointment.add(appointmentList.get(i).getDataAppointmentRadiology());
                } else {
                    count = 0;
                }
            }
        }

        return newAppointment;
    }
    public List<String> verificaHoursRadiology(String dataD) {
        List<AppointmentRadiology> appointmentList = appointmentRadiologyRepository.findAll();
        List<String> allHours = new ArrayList<>(Arrays.asList("09:00","09:15","09:30","09:45",
                "10:00","10:15","10:30","10:45","11:00","11:15","11:30","11:45",
                "12:00","12:15","12:30","12:45","14:00","14:15","14:30","14:45",
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
    public String deltete(){
        for (int i = 0; i < appointmentHematologyRepository.findAll().size(); i++) {

                if (appointmentHematologyRepository.findAll().get(i).getDataAppointmentHematology() == null){
                    appointmentHematologyRepository.delete(appointmentHematologyRepository.findAll().get(i));
                }

        }
        return "done";
            }
}


