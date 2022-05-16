package com.medicalclinicapp.medicalclinicapp.security.services;


import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentRadiology;
import com.medicalclinicapp.medicalclinicapp.models.RadiologyResult;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentHematologyRepository;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentRadiologyRepository;


import com.medicalclinicapp.medicalclinicapp.security.repository.ImagistRepository;
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
public class ImagistService {

    @Autowired
    private AppointmentRadiologyRepository appointmentRadiologyRepository;


    public List<AppointmentRadiology> getAllAppointmentRadiology(){
        List<AppointmentRadiology> appointmentRadiologyList  =appointmentRadiologyRepository.findAll();
        return appointmentRadiologyList;
    }
    public List<AppointmentRadiology> getAllTodayAppointmentRadiology(){
        List<AppointmentRadiology> appointmentRadiologyList = new ArrayList<>();
        for (int i = 0; i < appointmentRadiologyRepository.findAll().size(); i++) {
            {
                Date curentData = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                if (sdf.format(curentData).equals(appointmentRadiologyRepository.findAll().get(i).getDataAppointmentRadiology())) {
                    appointmentRadiologyList.add(appointmentRadiologyRepository.findAll().get(i));
                }
            }
        }
        return appointmentRadiologyList;
    }


    public RadiologyResult addRadiologyResult(RadiologyResult radiologyResult){
        if(radiologyResult.getCt() == null || radiologyResult.getCt().trim().isEmpty()){
            radiologyResult.setCt("https://adminassets.devops.arabiaweather.com/sites/default/files/field/image/mountains.jpg");
        }
        return radiologyResult;
    }

}
