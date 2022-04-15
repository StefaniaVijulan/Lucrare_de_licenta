package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.Hematology;
import com.medicalclinicapp.medicalclinicapp.models.Radiology;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentHematologyRepository;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentRadiologyRepository;
import com.medicalclinicapp.medicalclinicapp.repository.HematologyRepository;
import com.medicalclinicapp.medicalclinicapp.repository.RadiologyRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Hematolog;
import com.medicalclinicapp.medicalclinicapp.security.repository.HematologRepository;
import com.medicalclinicapp.medicalclinicapp.security.repository.ImagistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ImagistService {

    @Autowired
    private AppointmentRadiologyRepository appointmentRadiologyRepository;

    @Autowired
    private RadiologyRepository radiologyRepository;

    @Autowired
    private ImagistRepository imagistRepository;


    public Radiology addConsultationRadiology(Long idAppointment, Radiology Radiology, Principal principal) throws Exception {
        if(radiologyRepository.existsById(hematology.getId()))
            throw new Exception("Din consultation it was done");
        String username = principal.getName();
        Hematolog hematolog = this.hematologRepository.findByCnp(username);
        hematology.setHematolog(hematolog);

        for(int i=0; i<appointmentHematologyRepository.findAll().size(); i++){
            if(appointmentHematologyRepository.findAll().get(i).getId().equals(idAppointment)){
                hematology.setAppointmentHematology(appointmentHematologyRepository.findAll().get(i));
            }}


        hematologyRepository.save(hematology);
        return hematology;
    }
}
