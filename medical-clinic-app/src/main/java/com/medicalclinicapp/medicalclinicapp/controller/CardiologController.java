package com.medicalclinicapp.medicalclinicapp.controller;


import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentRadiology;
import com.medicalclinicapp.medicalclinicapp.security.services.CardiologService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CardiologController {

    @Autowired
    private CardiologService cardiologService;

    @PostMapping("/cardiolog/addAppointmentHematology")
    public AppointmentHematology addAppointmentHematology(@RequestBody AppointmentHematology appointment, Principal principal) throws Exception {

        return cardiologService.addAppointmentHematology(appointment, principal);
    }
    @PostMapping("/cardiolog/addAppointmentRadiology")
    public AppointmentRadiology addAppointmentRadiology(@RequestBody AppointmentRadiology appointment, Principal principal) throws Exception {

        return cardiologService.addAppointmentRadiology(appointment, principal);
    }
}
