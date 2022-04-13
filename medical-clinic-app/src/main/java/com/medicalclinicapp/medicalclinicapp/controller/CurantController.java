package com.medicalclinicapp.medicalclinicapp.controller;


import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentRadiology;
import com.medicalclinicapp.medicalclinicapp.security.services.CurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class CurantController {

    @Autowired
    private CurantService curantService;

    @PostMapping("/curant/addAppointmentHematology")
    public AppointmentHematology addAppointmentHematology(@RequestBody AppointmentHematology appointment, Principal principal) throws Exception {

        return curantService.addAppointmentHematology(appointment, principal);
    }
    @PostMapping("/curant/addAppointmentRadiology")
    public AppointmentRadiology addAppointmentRadiology(@RequestBody AppointmentRadiology appointment, Principal principal) throws Exception {

        return curantService.addAppointmentRadiology(appointment, principal);
    }
}
