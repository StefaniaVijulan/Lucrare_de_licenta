package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;

import com.medicalclinicapp.medicalclinicapp.models.AppointmentRadiology;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentHematologyRepository;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentRadiologyRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Curant;
import com.medicalclinicapp.medicalclinicapp.security.repository.CurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CurantService {

    @Autowired
    private CurantRepository curantRepository;

    @Autowired
    private AppointmentHematologyRepository appointmentHematologyRepository;

    @Autowired
    private AppointmentRadiologyRepository appointmentRadiologyRepository;


    public AppointmentHematology addAppointmentHematology(AppointmentHematology appointment, Principal principal) throws Exception {
        if(appointmentHematologyRepository.existsByDataAppointment(appointment.getDataAppointment()) &&
                appointmentHematologyRepository.existsByHourAppointment(appointment.getHourAppointment()) &&
                appointmentHematologyRepository.existsByMinAppointment(appointment.getMinAppointment()))
            throw new Exception("Hospitalization exist");
        String username = principal.getName();
        Curant curant = this.curantRepository.findByCnp(username);
        appointment.setCurant(curant);

        appointmentHematologyRepository.save(appointment);
        return appointment;
    }
    public AppointmentRadiology addAppointmentRadiology(AppointmentRadiology appointment, Principal principal) throws Exception {
        if(appointmentRadiologyRepository.existsByDataAppointment(appointment.getDataAppointment()) &&
                appointmentRadiologyRepository.existsByHourAppointment(appointment.getHourAppointment()) &&
                appointmentRadiologyRepository.existsByMinAppointment(appointment.getMinAppointment()))
            throw new Exception("Hospitalization exist");
        String username = principal.getName();
        Curant curant = this.curantRepository.findByCnp(username);
        appointment.setCurant(curant);

        appointmentRadiologyRepository.save(appointment);
        return appointment;
    }
}
