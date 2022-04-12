package com.medicalclinicapp.medicalclinicapp.security.services;

import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
import com.medicalclinicapp.medicalclinicapp.models.Patient;
import com.medicalclinicapp.medicalclinicapp.repository.AppointmentRepository;
import com.medicalclinicapp.medicalclinicapp.security.models.Curant;
import com.medicalclinicapp.medicalclinicapp.security.models.Secretary;
import com.medicalclinicapp.medicalclinicapp.security.repository.CurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CurantService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CurantRepository curantRepository;
    public Appointment addAppointment(Appointment appointment, Principal principal) throws Exception {
        if(appointmentRepository.existsByDataAppointment(appointment.getDataAppointment()) &&
                appointmentRepository.existsByHourAppointment(appointment.getHourAppointment()))
            throw new Exception("Hospitalization exist");
        String username = principal.getName();
        Curant curant = this.curantRepository.findByCnp(username);
        appointment.setCurant(curant);

        appointmentRepository.save(appointment);
        return appointment;
    }
}
