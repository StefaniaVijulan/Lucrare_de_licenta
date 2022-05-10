package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.*;

import com.medicalclinicapp.medicalclinicapp.repository.AppointmentRepository;
import com.medicalclinicapp.medicalclinicapp.repository.FisaPatientRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.*;
import com.medicalclinicapp.medicalclinicapp.security.repository.CardiologRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardiologService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private FisaPatientRepository fisaPatientRepository;

    public List<Appointment> getAllSpecificAppointment(String cnp){
        List<Appointment> appointmentList = new ArrayList<>();
        for(int i=0; i<appointmentRepository.findAll().size(); i++){
            if(appointmentRepository.findAll().get(i).getCardiolog().getCnp().equals(cnp))
                appointmentList.add(appointmentRepository.findAll().get(i));
        }
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
       /* fisaPatient1.setBloodTypePatient(fisaPatient.getBloodTypePatient());
        fisaPatient1.setRhPatient(fisaPatient.getRhPatient());
        fisaPatient1.setAllergyPatient(fisaPatient.getAllergyPatient());

        //Anamneza
        fisaPatient1.setFamilyHistory(fisaPatient.getFamilyHistory());
        fisaPatient1.setPersonalHistory(fisaPatient.getPersonalHistory());
        fisaPatient1.setLifeAndWorkConditional(fisaPatient.getLifeAndWorkConditional());
        fisaPatient1.setBehavior(fisaPatient.getBehavior());
        fisaPatient1.setPillsHistory(fisaPatient.getPillsHistory());

       //Examen obiectiv
        fisaPatient1.setGeneralCondition(fisaPatient.getGeneralCondition());
        fisaPatient1.setWaist(fisaPatient.getWaist());
        fisaPatient1.setWeight(fisaPatient.getWeight());
        fisaPatient1.setNutritionalStatus(fisaPatient.getNutritionalStatus());
        fisaPatient1.setGanglionSystem(fisaPatient.getGanglionSystem());
        fisaPatient1.setConnectiveTissue(fisaPatient.getConnectiveTissue());

        fisaPatient1.setCardiovascularSystem(fisaPatient.getCardiovascularSystem());


        fisaPatientRepository.save(fisaPatient1);*/
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
}
    /*
    @Autowired
    private AppointmentHematologyRepository appointmentHematologyRepository;

    @Autowired
    private HospitalizationRepository hospitalizationRepository;

    @Autowired
    private AppointmentRadiologyRepository appointmentRadiologyRepository;*/
/*

    public AppointmentHematology addAppointmentHematology(AppointmentHematology appointment, Principal principal) throws Exception {
        if(appointmentHematologyRepository.existsByDataAppointment(appointment.getDataAppointment()) &&
                appointmentHematologyRepository.existsByHourAppointment(appointment.getHourAppointment()) &&
                appointmentHematologyRepository.existsByMinAppointment(appointment.getMinAppointment()))
            throw new Exception("Hospitalization exist");
        String username = principal.getName();
        Cardiolog cardiolog = this.cardiologRepository.findByCnp(username);
       // appointment.setCardiolog(cardiolog);

        appointmentHematologyRepository.save(appointment);
        return appointment;
    }
    public AppointmentRadiology addAppointmentRadiology(AppointmentRadiology appointment, Principal principal) throws Exception {
        if(appointmentRadiologyRepository.existsByDataAppointment(appointment.getDataAppointment()) &&
                appointmentRadiologyRepository.existsByHourAppointment(appointment.getHourAppointment()) &&
                appointmentRadiologyRepository.existsByMinAppointment(appointment.getMinAppointment()))
            throw new Exception("Hospitalization exist");
        String username = principal.getName();
        Cardiolog cardiolog = this.cardiologRepository.findByCnp(username);
        //appointment.setCardiolog(cardiolog);

        appointmentRadiologyRepository.save(appointment);
        return appointment;
    }

}*/