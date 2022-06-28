package com.medicalclinicapp.medicalclinicapp.security.services;


import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentRadiology;
import com.medicalclinicapp.medicalclinicapp.models.HematologyResult;
import com.medicalclinicapp.medicalclinicapp.models.RadiologyResult;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentHematologyRepository;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentRadiologyRepository;


import com.medicalclinicapp.medicalclinicapp.repository.HematologyResultRepository;
import com.medicalclinicapp.medicalclinicapp.repository.RadiologyResultRepository;
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

    @Autowired
    private RadiologyResultRepository radiologyResultRepository;

    public List<AppointmentRadiology> getAllAppointmentRadiology(){
        List<AppointmentRadiology> appointmentRadiologyList =appointmentRadiologyRepository.findAll();
        return appointmentRadiologyList;
    }
    public List<AppointmentRadiology> getAllTodayAppointmentRadiology(){
        List<AppointmentRadiology> appointmentList = new ArrayList<>();
        for (int i = 0; i < appointmentRadiologyRepository.findAll().size(); i++) {
            {
                Date curentData = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                if (sdf.format(curentData).equals(appointmentRadiologyRepository.findAll().get(i).getDataAppointmentRadiology())) {
                    if(appointmentRadiologyRepository.findAll().get(i).getDone()==null)
                        appointmentList.add(appointmentRadiologyRepository.findAll().get(i));
                }
            }
        }
        return appointmentList;
    }
    public int seeAppointment(Long idA){
        for (int i = 0; i < appointmentRadiologyRepository.findAll().size(); i++) {
            if(appointmentRadiologyRepository.findAll().get(i).getId() == idA){
                appointmentRadiologyRepository.findAll().get(i).setDone(true);
                appointmentRadiologyRepository.save(appointmentRadiologyRepository.findAll().get(i));
                RadiologyResult radiologyResult = new RadiologyResult();
                radiologyResult.setAppointmentRadiology(appointmentRadiologyRepository.findAll().get(i));
                radiologyResultRepository.save(radiologyResult);
                break;
            }
        }
        //"Consultatia a fost realizata cu succes!"
        return 1;
    }
    public List<RadiologyResult> seeAppointmentsWithoutResult() {
        List<RadiologyResult> radiologyResults = new ArrayList<>();
        for (int i = 0; i < radiologyResultRepository.findAll().size(); i++) {
            if (radiologyResultRepository.findAll().get(i).getDone() == null) {
                radiologyResults.add(radiologyResultRepository.findAll().get(i));
            }
        }
        return radiologyResults;
    }

    public List<RadiologyResult> seeAllResult() {
        List<RadiologyResult> radiologyResults = new ArrayList<>();
        for (int i = 0; i < radiologyResultRepository.findAll().size(); i++) {
            if (radiologyResultRepository.findAll().get(i).getDone() != null) {
                radiologyResults.add(radiologyResultRepository.findAll().get(i));
            }
        }
        return radiologyResults;
    }

}
