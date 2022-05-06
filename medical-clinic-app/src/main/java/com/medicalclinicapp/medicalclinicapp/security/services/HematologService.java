package com.medicalclinicapp.medicalclinicapp.security.services;
/*
import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.Hematology;
import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentHematologyRepository;
import com.medicalclinicapp.medicalclinicapp.repository.HematologyRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Hematolog;
import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import com.medicalclinicapp.medicalclinicapp.security.repository.HematologRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class HematologService {

    @Autowired
    private AppointmentHematologyRepository appointmentHematologyRepository;

    @Autowired
    private HematologyRepository hematologyRepository;

    @Autowired
    private HematologRepository hematologRepository;*/
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