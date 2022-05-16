package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentRadiology;
import com.medicalclinicapp.medicalclinicapp.models.RadiologyResult;
import com.medicalclinicapp.medicalclinicapp.security.repository.ImagistRepository;
import com.medicalclinicapp.medicalclinicapp.security.services.HematologService;
import com.medicalclinicapp.medicalclinicapp.security.services.ImagistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ImagistController {

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

        @PostMapping("/imagist/addRadiologyResult")
        public RadiologyResult getResult(@RequestBody RadiologyResult radiologyResult){

            return imagistService.addRadiologyResult(radiologyResult);
        }


}
