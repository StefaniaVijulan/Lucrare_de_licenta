package com.medicalclinicapp.medicalclinicapp.controller;

import com.medicalclinicapp.medicalclinicapp.models.Appointment;
import com.medicalclinicapp.medicalclinicapp.models.AppointmentHematology;
import com.medicalclinicapp.medicalclinicapp.models.HematologyResult;
import com.medicalclinicapp.medicalclinicapp.security.services.HematologService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/hematolog/appointmentHematologyDone")
    public int seeAppointment(@RequestParam("idA") Long idA){
        return hematologService.seeAppointment(idA);
    }
    @GetMapping("/hematolog/seeAppointmentWithoutResult")
    public List<HematologyResult> seeAppointmentR()
    {
        return hematologService.seeAppointmentsWithoutResult();
    }
    @PutMapping("/hematolog/editAppointmentResult")
    public int editResultHema(@RequestParam("idR")Long idR, @RequestBody HematologyResult hematologyResult){
        return hematologService.resultDone(idR, hematologyResult);

    }

}
