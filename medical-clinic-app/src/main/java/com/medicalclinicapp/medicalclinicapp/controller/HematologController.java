package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.security.services.HematologService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class HematologController {
    @Autowired
    private HematologService hematologService;

    @GetMapping("/hematolog/allAppointmentHematology")
    public List<AppointmentHematology> getAppointmentHematology(){
        return hematologService.getAllAppointmentHematology();
    }

    @GetMapping("/hematolog/allTodayAppointmentHematology")
    public List<AppointmentHematology> getTodayAppointmentHematology(){
        return hematologService.getAllTodayAppointmentHematology();
    }
}
