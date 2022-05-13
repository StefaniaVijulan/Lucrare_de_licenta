package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentRadiology;
import com.medicalclinicapp.medicalclinicapp.security.repository.ImagistRepository;
import com.medicalclinicapp.medicalclinicapp.security.services.HematologService;
import com.medicalclinicapp.medicalclinicapp.security.services.ImagistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class ImagistController {

    @RestController
    @CrossOrigin(origins = "http://localhost:4200")
    @RequiredArgsConstructor
    public class RadiologyController {
        @Autowired
        private ImagistService imagistService;

        @GetMapping("/imagist/allAppointmentRadiology")
        public List<AppointmentRadiology> getAppointmentRadiology(){
            return imagistService.getAllAppointmentRadiology();
        }

        @GetMapping("/imagist/allTodayAppointmentRadiology")
        public List<AppointmentRadiology> getTodayAppointmentHematology(){
            return imagistService.getAllTodayAppointmentRadiology();
        }
    }

}
