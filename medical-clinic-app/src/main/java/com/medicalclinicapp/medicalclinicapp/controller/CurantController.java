package com.medicalclinicapp.medicalclinicapp.controller;


import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.models.Hospitalization;
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
    @PostMapping("/curant/addAppointment")
    public Appointment addAppoitment(@RequestBody Appointment appointment, Principal principal) throws Exception {

        return curantService.addAppointment(appointment, principal);
    }

}
