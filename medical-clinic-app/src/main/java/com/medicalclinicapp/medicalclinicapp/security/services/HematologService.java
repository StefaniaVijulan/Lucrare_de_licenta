package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentHematologyRepository;

import com.medicalclinicapp.medicalclinicapp.security.repository.HematologRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HematologService {

    @Autowired
    private AppointmentHematologyRepository appointmentHematologyRepository;

    public List<AppointmentHematology> getAllAppointmentHematology(){
        List<AppointmentHematology> appointmentHematologyList =appointmentHematologyRepository.findAll();
        return appointmentHematologyList;
    }
    public List<AppointmentHematology> getAllTodayAppointmentHematology(){
        List<AppointmentHematology> appointmentList = new ArrayList<>();
        for (int i = 0; i < appointmentHematologyRepository.findAll().size(); i++) {
            {
                Date curentData = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                if (sdf.format(curentData).equals(appointmentHematologyRepository.findAll().get(i).getDataAppointmentHematology())) {
                    appointmentList.add(appointmentHematologyRepository.findAll().get(i));
                }
            }
        }
        return appointmentList;
    }

}


    /*
    public Hematology addConsultationHematology(Long idAppointment, Hematology hematology, Principal principal) throws Exception {
        if(hematologyRepository.existsById(hematology.getId()))
            throw new Exception("Din consultation it was done");
        String username = principal.getName();
        Hematolog hematolog = this.hematologRepository.findByCnp(username);
      //  hematology.setHematolog(hematolog);

        for(int i=0; i<appointmentHematologyRepository.findAll().size(); i++){
            if(appointmentHematologyRepository.findAll().get(i).getId().equals(idAppointment)){
                hematology.setAppointmentHematology(appointmentHematologyRepository.findAll().get(i));
            }}


        hematologyRepository.save(hematology);
        return hematology;
    }
}
        */