package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentRadiology;
import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentHematologyRepository;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentRadiologyRepository;
import com.medicalclinicapp.medicalclinicapp.repository.HospitalizationRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Cardiolog;
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
    private CardiologRepository cardiologRepository;

    @Autowired
    private AppointmentHematologyRepository appointmentHematologyRepository;

    @Autowired
    private HospitalizationRepository hospitalizationRepository;

    @Autowired
    private AppointmentRadiologyRepository appointmentRadiologyRepository;


    public AppointmentHematology addAppointmentHematology(AppointmentHematology appointment, Principal principal) throws Exception {
        if(appointmentHematologyRepository.existsByDataAppointment(appointment.getDataAppointment()) &&
                appointmentHematologyRepository.existsByHourAppointment(appointment.getHourAppointment()) &&
                appointmentHematologyRepository.existsByMinAppointment(appointment.getMinAppointment()))
            throw new Exception("Hospitalization exist");
        String username = principal.getName();
        Cardiolog cardiolog = this.cardiologRepository.findByCnp(username);
        appointment.setCardiolog(cardiolog);

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
        appointment.setCardiolog(cardiolog);

        appointmentRadiologyRepository.save(appointment);
        return appointment;
    }

}